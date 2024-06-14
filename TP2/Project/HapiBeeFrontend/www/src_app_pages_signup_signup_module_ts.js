"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_signup_signup_module_ts"],{

/***/ 17110:
/*!***********************************************!*\
  !*** ./src/app/pages/signup/signup.module.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "SignupPageModule": () => (/* binding */ SignupPageModule)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _signup_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./signup.page */ 64374);








const routes = [
    {
        path: '',
        component: _signup_page__WEBPACK_IMPORTED_MODULE_0__.SignupPage,
    }
];
let SignupPageModule = class SignupPageModule {
};
SignupPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_1__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_2__.NgModule)({
        imports: [_angular_common__WEBPACK_IMPORTED_MODULE_3__.CommonModule, _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormsModule, _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.IonicModule, _angular_router__WEBPACK_IMPORTED_MODULE_6__.RouterModule.forChild(routes), _ngx_translate_core__WEBPACK_IMPORTED_MODULE_7__.TranslateModule],
        declarations: [_signup_page__WEBPACK_IMPORTED_MODULE_0__.SignupPage],
    })
], SignupPageModule);



/***/ }),

/***/ 64374:
/*!*********************************************!*\
  !*** ./src/app/pages/signup/signup.page.ts ***!
  \*********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "SignupPage": () => (/* binding */ SignupPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _signup_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./signup.page.html?ngResource */ 45722);
/* harmony import */ var _signup_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./signup.page.scss?ngResource */ 62124);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/user/user.service */ 9709);







let SignupPage = class SignupPage {
    constructor(navController, userService, toastController, translateService) {
        this.navController = navController;
        this.userService = userService;
        this.toastController = toastController;
        this.translateService = translateService;
        // The account fields for the signup form
        this.account = {
            login: '',
            email: '',
            firstName: '',
            lastName: '',
            password: '',
            langKey: 'en',
            rgpd: false
        };
        this.translateService.get(['SIGNUP_ERROR', 'SIGNUP_SUCCESS', 'EXISTING_USER_ERROR', 'INVALID_PASSWORD_ERROR']).subscribe(values => {
            this.signupErrorString = values.SIGNUP_ERROR;
            this.signupSuccessString = values.SIGNUP_SUCCESS;
            this.existingUserError = values.EXISTING_USER_ERROR;
            this.invalidPasswordError = values.INVALID_PASSWORD_ERROR;
        });
    }
    ngOnInit() { }
    termsAndConditions() {
        this.navController.navigateForward('/signup/terms-conditions');
    }
    doSignup() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            // set login to same as email
            this.account.login = this.account.email;
            // Attempt to login in through our User service
            if (!this.account.rgpd) {
                const toast = yield this.toastController.create({
                    message: "Tem que aceitar os termos e condições para se registar na aplicação.",
                    duration: 3000,
                    position: 'middle',
                });
                toast.present();
                return;
            }
            this.userService.signup(this.account).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastController.create({
                    message: this.signupSuccessString,
                    duration: 3000,
                    position: 'top',
                });
                toast.present();
            }), (response) => (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
                // Unable to sign up
                const error = JSON.parse(response.error);
                let displayError = this.signupErrorString;
                if (response.status === 400 && error.type.includes('already-used')) {
                    displayError = this.existingUserError;
                }
                else if (response.status === 400 &&
                    error.message === 'error.validation' &&
                    error.fieldErrors[0].field === 'password' &&
                    error.fieldErrors[0].message === 'Size') {
                    displayError = this.invalidPasswordError;
                }
                const toast = yield this.toastController.create({
                    message: displayError,
                    duration: 3000,
                    position: 'middle',
                });
                toast.present();
            }));
        });
    }
};
SignupPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_4__.NavController },
    { type: _services_user_user_service__WEBPACK_IMPORTED_MODULE_2__.UserService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_4__.ToastController },
    { type: _ngx_translate_core__WEBPACK_IMPORTED_MODULE_5__.TranslateService }
];
SignupPage = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'app-signup',
        template: _signup_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
        styles: [_signup_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__]
    })
], SignupPage);



/***/ }),

/***/ 62124:
/*!**********************************************************!*\
  !*** ./src/app/pages/signup/signup.page.scss?ngResource ***!
  \**********************************************************/
/***/ ((module) => {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzaWdudXAucGFnZS5zY3NzIn0= */";

/***/ }),

/***/ 45722:
/*!**********************************************************!*\
  !*** ./src/app/pages/signup/signup.page.html?ngResource ***!
  \**********************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>{{ 'SIGNUP_TITLE' | translate }}</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <form (submit)=\"doSignup()\">\n    <ion-list>\n      <ion-item>\n        <ion-label position=\"floating\">{{ 'FIRST_NAME' | translate }}</ion-label>\n        <ion-input type=\"string\" [(ngModel)]=\"account.firstName\" name=\"firstName\"></ion-input>\n      </ion-item>\n\n      <ion-item>\n        <ion-label position=\"floating\">{{ 'LAST_NAME' | translate }}</ion-label>\n        <ion-input type=\"string\" [(ngModel)]=\"account.lastName\" name=\"lastName\"></ion-input>\n      </ion-item>\n\n      <ion-item>\n        <ion-label position=\"floating\">{{ 'EMAIL' | translate }}</ion-label>\n        <ion-input type=\"email\" [(ngModel)]=\"account.email\" name=\"email\"></ion-input>\n      </ion-item>\n\n      <ion-item>\n        <ion-label position=\"floating\">{{ 'PASSWORD' | translate }}</ion-label>\n        <ion-input type=\"password\" [(ngModel)]=\"account.password\" name=\"password\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label >Li e Declaro que aceito os <a routerLink=\"/terms-conditions\" >Termos e Condições de Utilização</a>.</ion-label>\n        <ion-checkbox type=\"rgpd\" [(ngModel)]=\"account.rgpd\" name=\"rgpd\"></ion-checkbox>\n      </ion-item>\n      <ion-row class=\"ion-padding\">\n        <ion-col>\n          <ion-button expand=\"block\" type=\"submit\" color=\"primary\">{{ 'SIGNUP_BUTTON' | translate }}</ion-button>\n        </ion-col>\n      </ion-row>\n    </ion-list>\n  </form>\n</ion-content>\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_signup_signup_module_ts.js.map