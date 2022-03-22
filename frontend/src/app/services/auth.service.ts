import { Injectable } from '@angular/core';
import createAuth0Client, { User } from '@auth0/auth0-spa-js';
import Auth0Client from '@auth0/auth0-spa-js/dist/typings/Auth0Client';
import { from, of, Observable, BehaviorSubject, combineLatest, throwError } from 'rxjs';
import { tap, catchError, concatMap, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ClinicUser } from '../models/clinic-user';
import { UserService } from './user.service';
import { UserAdd } from '../models/user-add';
import { UserGet } from '../models/user-get';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private clientId = 'vPFvqhsTHL1PfkcDkv9akb9LlNLxls15';
  clinicUser?: ClinicUser;
  token?: string;

  auth0Client$ = (from(
    createAuth0Client({
      domain: 'eclinic.eu.auth0.com',
      client_id: this.clientId,
      audience: 'clinic.clinic-api.com',
      redirect_uri: `${window.location.origin}/callback`,
      sso: false
    }),
  ) as Observable<Auth0Client>).pipe(
    shareReplay(1), // Every subscription receives the same shared value
    catchError(err => throwError(err)),
  );

  isAuthenticated$ = this.auth0Client$.pipe(
    concatMap((client: Auth0Client) => from(client.isAuthenticated())),
    tap(res => (this.loggedIn = res)),
  );
  handleRedirectCallback$ = this.auth0Client$.pipe(
    concatMap((client: Auth0Client) => from(client.handleRedirectCallback())),
  );

  // Create subject and public observable of user profile data
  private userProfileSubject$ = new BehaviorSubject<any>(null);
  userProfile$ = this.userProfileSubject$.asObservable();
  // Create a local property for login status

  loggedIn: boolean = false;
  clinicUser_helper: ClinicUser = new ClinicUser();
  addClinicUser: UserAdd = new UserAdd();
  userExists = false;
  user: UserGet = new UserGet();

  constructor(private router: Router) {
  }

  // When calling, options can be passed if desired
  // https://auth0.github.io/auth0-spa-js/classes/auth0client.html#getuser
  getUser$(options?: any): Observable<any> {
    return this.auth0Client$.pipe(
      concatMap((client: Auth0Client) => from(client.getUser(options))),
      tap(user => this.userProfileSubject$.next(user)),
    );
  }

  getToken$(): Observable<string> {
    return this.auth0Client$.pipe(
      concatMap((client: Auth0Client) => from(client.getTokenSilently()))
    )
  }

  localAuthSetup() {
    // This should only be called on app initialization
    // Set up local authentication streams
    const checkAuth$ = this.isAuthenticated$.pipe(
      concatMap((loggedIn: boolean) => {
        if (loggedIn) {
          // If authenticated, get user and set in app
          // NOTE: you could pass options here if needed
          return this.getUser$();
        }
        // If not authenticated, return stream that emits 'false'
        return of(loggedIn);
      }),
    );

    checkAuth$.subscribe((response: { [key: string]: any } | boolean) => {
      // If authenticated, response will be user object
      // If not authenticated, response will be 'false'
      this.loggedIn = !!response;
    });
  }

  login(redirectPath: string = '') {
    // A desired redirect path can be passed to login method
    // (e.g., from a route guard)
    // Ensure Auth0 client instance exists
    this.auth0Client$.subscribe((client: Auth0Client) => {
      // Call method to log in
      client.loginWithRedirect({
        redirect_uri: `${window.location.origin}/callback`,
        appState: { target: redirectPath },
      });
    });
  }

  handleAuthCallback() {
    // Only the callback component should call this method
    // Call when app reloads after user logs in with Auth0
    let targetRoute: string; // Path to redirect to after login processsed
    const authComplete$ = this.handleRedirectCallback$.pipe(
      // Have client, now call method to handle auth callback redirect
      tap(cbRes => {
        // Get and set target redirect route from callback results
        targetRoute = cbRes.appState && cbRes.appState.target ? cbRes.appState.target : '/';
      }),
      concatMap(() => {
        // Redirect callback complete; get user, token and login status
        return combineLatest([this.getUser$(), this.getToken$(), this.isAuthenticated$]);
      }),
    );
    // Subscribe to authentication completion observable
    // Response will be an array of user, token and login status
    authComplete$.subscribe(([user, token, loggedIn]) => {
      localStorage.setItem("token", token as unknown as string);
      let authUser = user as User;
      this.clinicUser = {
        email: authUser.email,
        name: authUser.name,
        userid: authUser.sub?.substring(14, authUser.sub?.length).toString(),
      };
      this.token = token as unknown as string;

      localStorage.setItem("clinicUser", JSON.stringify(this.clinicUser));

      // Redirect to target route after callback processing
      this.router.navigate([targetRoute]);
      return new Observable();
    });
  }

  logout() {
    // Ensure Auth0 client instance exists
    this.auth0Client$.subscribe((client: Auth0Client) => {
      // Call method to log out
      client.logout({
        client_id: this.clientId,
        returnTo: `${window.location.origin}`,
      });
    });
  }
}
