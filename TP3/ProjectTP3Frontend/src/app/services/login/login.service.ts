import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AccountService } from '../auth/account.service';
import { AuthServerProvider } from '../auth/auth-jwt.service';
import { Beekeeper, BeekeeperService } from "../../pages/entities/beekeeper";
import { filter, map } from "rxjs/operators";
import { HttpResponse } from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(
    private accountService: AccountService,
    private beekeeperService: BeekeeperService,
    private authServerProvider: AuthServerProvider,
    private translate: TranslateService
  ) {}

  login(credentials, callback?) {
    const cb = callback || function () {};

    return new Promise((resolve, reject) => {
      this.authServerProvider.login(credentials).subscribe(
        data => {
          this.accountService.identity(true).then(account => {
            // After the login the language will be changed to
            // the language selected by the user during his registration
            if (account !== null) {
              this.beekeeperService.query({ 'userId.equals': localStorage.getItem('userId') }).pipe(
                filter((res: HttpResponse<Beekeeper[]>) => res.ok),
                map((res: HttpResponse<Beekeeper[]>) => res.body)
              )
                .subscribe(
                  (response: Beekeeper[]) => {
                    if (response.length != 0){
                      localStorage.setItem('beekeeperId', response[0].id.toString());
                    }
                  });
              this.translate.use(account.langKey);
            }
            resolve(data);
          });
          return cb();
        },
        err => {
          this.logout();
          reject(err);
          return cb(err);
        }
      );
    });
  }

  loginWithToken(jwt, rememberMe) {
    return this.authServerProvider.loginWithToken(jwt, rememberMe);
  }

  logout() {
    this.authServerProvider.logout().subscribe();
    this.accountService.authenticate(null);
  }
}
