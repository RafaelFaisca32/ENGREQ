import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { Observable, Subject } from 'rxjs';
import { Account } from 'src/model/account.model';
import { ApiService } from '../api/api.service';
import { NetworkCheckerService } from '../../services/network/network-checker.service';


@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private userIdentity: any;
  private authenticated = false;
  private authenticationState = new Subject<any>();

  constructor(private sessionStorage: SessionStorageService, private http: HttpClient,
    private network: NetworkCheckerService ) {}

  fetch(): Observable<HttpResponse<Account>> {
    return this.http.get<Account>(ApiService.API_URL + '/account', { observe: 'response' });
  }

  save(account: any): Observable<HttpResponse<any>> {
    return this.http.post(ApiService.API_URL + '/account', account, { observe: 'response' });
  }

  authenticate(identity) {
    this.userIdentity = identity;
    this.authenticated = identity !== null;
    this.authenticationState.next(this.userIdentity);
  }

  hasAnyAuthority(authorities: string[]): Promise<boolean> {
    return Promise.resolve(this.hasAnyAuthorityDirect(authorities));
  }

  hasAnyAuthorityDirect(authorities: string[]): boolean {
    if (!this.authenticated || !this.userIdentity || !this.userIdentity.authorities) {
      return false;
    }

    for (let i = 0; i < authorities.length; i++) {
      if (this.userIdentity.authorities.includes(authorities[i])) {
        return true;
      }
    }

    return false;
  }

  hasAuthority(authority: string): Promise<boolean> {
    if (!this.authenticated) {
      return Promise.resolve(false);
    }

    return this.identity().then(
      id => {
        return Promise.resolve(id.authorities && id.authorities.includes(authority));
      },
      () => {
        return Promise.resolve(false);
      }
    );
  }

  identity(force?: boolean): Promise<any> {
    if (force === true) {
      this.userIdentity = undefined;
    }

    // check and see if we have retrieved the userIdentity data from the server.
    // if we have, reuse it by immediately resolving
    if (this.userIdentity) {
      return Promise.resolve(this.userIdentity);
    }

    if(!this.network.onlineIndicator){
      this.authenticated = true;
      this.userIdentity = {
        "id": 1,
        "login": "offline",
        "firstName": "Offline User",
        "lastName": "offline",
        "email": "offline",
        "imageUrl": "",
        "activated": true,
        "langKey": "pt-pt",
        "createdBy": "system",
        "createdDate": null,
        "lastModifiedBy": "system",
        "lastModifiedDate": null,
        "authorities": [
            "ROLE_USER",
            "ROLE_ADMIN"
        ]
      }
      return Promise.resolve(this.userIdentity)
    }



    // retrieve the userIdentity data from the server, update the identity object, and then resolve.
    return this.fetch()
      .toPromise()
      .then(response => {
        const account = response.body;
        if (account) {
          console.log(account)
          this.userIdentity = account;
          localStorage.setItem("userId", this.userIdentity.id);
          this.authenticated = true;
          // After retrieve the account info, the language will be changed to
          // the user's preferred language configured in the account setting

          const langKey = this.sessionStorage.retrieve('locale') || this.userIdentity.langKey;
          // this.languageService.changeLanguage(langKey);
        } else {
          this.userIdentity = null;
          this.authenticated = false;
        }
        this.authenticationState.next(this.userIdentity);
        return this.userIdentity;
      })
      .catch(err => {
        this.userIdentity = null;
        this.authenticated = false;
        this.authenticationState.next(this.userIdentity);
        return null;
      });
  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  isIdentityResolved(): boolean {
    return this.userIdentity !== undefined;
  }

  getAuthenticationState(): Observable<any> {
    return this.authenticationState.asObservable();
  }

  getImageUrl(): string {
    return this.isIdentityResolved() ? this.userIdentity.imageUrl : null;
  }
}
