"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_account_account_module_ts"],{

/***/ 36868:
/*!*************************************************!*\
  !*** ./src/app/pages/account/account.module.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AccountPageModule": () => (/* binding */ AccountPageModule)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var src_app_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! src/app/services/auth/user-route-access.service */ 51284);
/* harmony import */ var _account_page__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./account.page */ 29610);









const routes = [
    {
        path: '',
        component: _account_page__WEBPACK_IMPORTED_MODULE_1__.AccountPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [src_app_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let AccountPageModule = class AccountPageModule {
};
AccountPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_3__.NgModule)({
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_4__.IonicModule, _angular_common__WEBPACK_IMPORTED_MODULE_5__.CommonModule, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormsModule, _angular_router__WEBPACK_IMPORTED_MODULE_7__.RouterModule.forChild(routes), _ngx_translate_core__WEBPACK_IMPORTED_MODULE_8__.TranslateModule],
        declarations: [_account_page__WEBPACK_IMPORTED_MODULE_1__.AccountPage],
    })
], AccountPageModule);



/***/ }),

/***/ 29610:
/*!***********************************************!*\
  !*** ./src/app/pages/account/account.page.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AccountPage": () => (/* binding */ AccountPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _account_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./account.page.html?ngResource */ 96216);
/* harmony import */ var _account_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./account.page.scss?ngResource */ 77110);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ 3184);




let AccountPage = class AccountPage {
};
AccountPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_3__.Component)({
        selector: 'app-account',
        template: _account_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
        styles: [_account_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__]
    })
], AccountPage);



/***/ }),

/***/ 77110:
/*!************************************************************!*\
  !*** ./src/app/pages/account/account.page.scss?ngResource ***!
  \************************************************************/
/***/ ((module) => {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhY2NvdW50LnBhZ2Uuc2NzcyJ9 */";

/***/ }),

/***/ 96216:
/*!************************************************************!*\
  !*** ./src/app/pages/account/account.page.html?ngResource ***!
  \************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-title> {{ 'TAB3_TITLE' | translate }} </ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content></ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_account_account_module_ts.js.map