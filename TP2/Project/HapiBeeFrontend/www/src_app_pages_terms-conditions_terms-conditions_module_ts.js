"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_terms-conditions_terms-conditions_module_ts"],{

/***/ 28671:
/*!*******************************************************************!*\
  !*** ./src/app/pages/terms-conditions/terms-conditions.module.ts ***!
  \*******************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TermsConditionsModule": () => (/* binding */ TermsConditionsModule)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _terms_conditions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./terms-conditions */ 64104);








const routes = [
    {
        path: '',
        component: _terms_conditions__WEBPACK_IMPORTED_MODULE_0__.TermsConditionsComponent,
    },
];
let TermsConditionsModule = class TermsConditionsModule {
};
TermsConditionsModule = (0,tslib__WEBPACK_IMPORTED_MODULE_1__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_2__.NgModule)({
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_3__.IonicModule, _angular_common__WEBPACK_IMPORTED_MODULE_4__.CommonModule, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormsModule, _angular_router__WEBPACK_IMPORTED_MODULE_6__.RouterModule.forChild(routes), _ngx_translate_core__WEBPACK_IMPORTED_MODULE_7__.TranslateModule],
        declarations: [_terms_conditions__WEBPACK_IMPORTED_MODULE_0__.TermsConditionsComponent],
    })
], TermsConditionsModule);



/***/ }),

/***/ 64104:
/*!************************************************************!*\
  !*** ./src/app/pages/terms-conditions/terms-conditions.ts ***!
  \************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TermsConditionsComponent": () => (/* binding */ TermsConditionsComponent)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _terms_conditions_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./terms-conditions.html?ngResource */ 35414);
/* harmony import */ var _terms_conditions_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./terms-conditions.scss?ngResource */ 53545);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ 3184);




let TermsConditionsComponent = class TermsConditionsComponent {
    constructor() { }
    ngOnInit() { }
};
TermsConditionsComponent.ctorParameters = () => [];
TermsConditionsComponent = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_3__.Component)({
        selector: 'app-terms-conditions',
        template: _terms_conditions_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
        styles: [_terms_conditions_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__]
    })
], TermsConditionsComponent);



/***/ }),

/***/ 53545:
/*!*************************************************************************!*\
  !*** ./src/app/pages/terms-conditions/terms-conditions.scss?ngResource ***!
  \*************************************************************************/
/***/ ((module) => {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJ0ZXJtcy1jb25kaXRpb25zLnNjc3MifQ== */";

/***/ }),

/***/ 35414:
/*!*************************************************************************!*\
  !*** ./src/app/pages/terms-conditions/terms-conditions.html?ngResource ***!
  \*************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons defaultHref=\"/signup\" slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Termos e condições</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <h1>Termos e Condições de Uso</h1>\n\n  <p>Bem-vindo aos Termos e Condições de Uso. Estes termos regem o acesso e uso deste serviço. Ao acessar ou utilizar este serviço, você concorda em cumprir estes termos. Por favor, leia atentamente antes de prosseguir.</p>\n\n  <h2>1. Aceitação dos Termos</h2>\n  <p>Ao acessar este serviço, você concorda em estar vinculado por estes termos e condições de uso, todas as leis e regulamentos aplicáveis e concorda que é responsável pelo cumprimento de todas as leis locais aplicáveis. Se você não concordar com algum destes termos, está proibido de usar ou acessar este serviço. Os materiais contidos neste serviço são protegidos pelas leis de direitos autorais e marcas comerciais aplicáveis.</p>\n\n  <h2>2. Uso do Serviço</h2>\n  <p>Este serviço é fornecido \"como está\". Fazemos o possível para garantir a precisão e a integridade das informações, mas não oferecemos garantias ou representações de qualquer tipo, expressas ou implícitas, sobre a precisão, confiabilidade ou disponibilidade do serviço.</p>\n\n  <h2>3. Privacidade</h2>\n  <p>Respeitamos a sua privacidade. Todas as informações pessoais fornecidas serão tratadas de acordo com nossa Política de Privacidade. Ao usar este serviço, você concorda com a coleta e uso de informações de acordo com a nossa Política de Privacidade.</p>\n\n  <h2>4. Modificações nos Termos</h2>\n  <p>Reservamos o direito de revisar estes termos de uso a qualquer momento, sem aviso prévio. Ao usar este serviço, você concorda em ficar vinculado à versão atual destes termos e condições de uso.</p>\n\n  <h2>5. Contacto</h2>\n  <p>Se você tiver alguma dúvida sobre estes Termos e Condições, entre em contacto conosco através de TELEFONE: +351 22 83 40 500.<p>\n  <p>Ao continuar a usar este serviço, você indica sua aceitação destes termos e condições. Se você não concorda com estes termos, por favor, não continue a utilizar este serviço.</p>\n</ion-content>\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_terms-conditions_terms-conditions_module_ts.js.map