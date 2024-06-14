"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_entities_module_ts"],{

/***/ 70043:
/*!***************************************************!*\
  !*** ./src/app/pages/entities/entities.module.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "EntitiesPageModule": () => (/* binding */ EntitiesPageModule)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var src_app_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! src/app/services/auth/user-route-access.service */ 51284);
/* harmony import */ var _entities_page__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./entities.page */ 57887);









const routes = [
    {
        path: '',
        component: _entities_page__WEBPACK_IMPORTED_MODULE_1__.EntitiesPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [src_app_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'apiary',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_zone_index_ts"), __webpack_require__.e("src_app_pages_entities_apiary_apiary_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./apiary/apiary.module */ 59054)).then(m => m.ApiaryPageModule),
    },
    {
        path: 'beekeeper',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("common"), __webpack_require__.e("src_app_pages_entities_beekeeper_beekeeper_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./beekeeper/beekeeper.module */ 87382)).then(m => m.BeekeeperPageModule),
    },
    {
        path: 'hive',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_hive_index_ts-src_app_pages_entities_transhumance-request_index_ts"), __webpack_require__.e("src_app_pages_entities_hive_hive_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./hive/hive.module */ 55873)).then(m => m.HivePageModule),
    },
    {
        path: 'frame',
        loadChildren: () => __webpack_require__.e(/*! import() */ "src_app_pages_entities_frame_frame_module_ts").then(__webpack_require__.bind(__webpack_require__, /*! ./frame/frame.module */ 21354)).then(m => m.FramePageModule),
    },
    {
        path: 'zone',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_zone_index_ts"), __webpack_require__.e("src_app_pages_entities_zone_zone_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./zone/zone.module */ 74602)).then(m => m.ZonePageModule),
    },
    {
        path: 'inspection',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_disease_index_ts-src_app_pages_entities_inspection_index_ts"), __webpack_require__.e("src_app_pages_entities_inspection_inspection_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./inspection/inspection.module */ 45699)).then(m => m.InspectionPageModule),
    },
    {
        path: 'disease',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_disease_index_ts-src_app_pages_entities_inspection_index_ts"), __webpack_require__.e("src_app_pages_entities_disease_disease_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./disease/disease.module */ 65739)).then(m => m.DiseasePageModule),
    },
    {
        path: 'unfolding',
        loadChildren: () => __webpack_require__.e(/*! import() */ "src_app_pages_entities_unfolding_unfolding_module_ts").then(__webpack_require__.bind(__webpack_require__, /*! ./unfolding/unfolding.module */ 35197)).then(m => m.UnfoldingPageModule),
    },
    {
        path: 'crest',
        loadChildren: () => __webpack_require__.e(/*! import() */ "src_app_pages_entities_crest_crest_module_ts").then(__webpack_require__.bind(__webpack_require__, /*! ./crest/crest.module */ 44180)).then(m => m.CrestPageModule),
    },
    {
        path: 'transhumance-request',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_hive_index_ts-src_app_pages_entities_transhumance-request_index_ts"), __webpack_require__.e("src_app_pages_entities_transhumance-request_transhumance-request_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./transhumance-request/transhumance-request.module */ 93956)).then(m => m.TranshumanceRequestPageModule),
    },
    {
        path: 'annual-inventory-declaration',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_annual-inventory-declaration_index_ts-src_app_pages_entities_a-225784"), __webpack_require__.e("src_app_pages_entities_annual-inventory-declaration_annual-inventory-declaration_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./annual-inventory-declaration/annual-inventory-declaration.module */ 99040)).then(m => m.AnnualInventoryDeclarationPageModule),
    },
    {
        path: 'apiary-information',
        loadChildren: () => Promise.all(/*! import() */[__webpack_require__.e("default-src_app_pages_entities_apiary_index_ts"), __webpack_require__.e("default-src_app_pages_entities_annual-inventory-declaration_index_ts-src_app_pages_entities_a-225784"), __webpack_require__.e("src_app_pages_entities_apiary-information_apiary-information_module_ts")]).then(__webpack_require__.bind(__webpack_require__, /*! ./apiary-information/apiary-information.module */ 62273)).then(m => m.ApiaryInformationPageModule),
    },
    /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];
let EntitiesPageModule = class EntitiesPageModule {
};
EntitiesPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_3__.NgModule)({
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_4__.IonicModule, _angular_common__WEBPACK_IMPORTED_MODULE_5__.CommonModule, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormsModule, _angular_router__WEBPACK_IMPORTED_MODULE_7__.RouterModule.forChild(routes), _ngx_translate_core__WEBPACK_IMPORTED_MODULE_8__.TranslateModule],
        declarations: [_entities_page__WEBPACK_IMPORTED_MODULE_1__.EntitiesPage],
    })
], EntitiesPageModule);



/***/ }),

/***/ 57887:
/*!*************************************************!*\
  !*** ./src/app/pages/entities/entities.page.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "EntitiesPage": () => (/* binding */ EntitiesPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _entities_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./entities.page.html?ngResource */ 94668);
/* harmony import */ var _entities_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./entities.page.scss?ngResource */ 50537);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/angular */ 93819);





let EntitiesPage = class EntitiesPage {
    constructor(navController) {
        this.navController = navController;
        this.entities = [
            { name: 'Criar um Apiário', component: 'ApiaryPage', route: 'apiary/new', subroute: 'entities' },
            { name: 'Lista de Apiários', component: 'ApiaryPage', route: 'apiary', subroute: 'entities' },
            { name: 'Efetuar uma inspeção', component: 'ApiaryPage', route: 'apiary', subroute: 'entities', from: 'createInspection' },
            { name: 'Realizar a Cresta', component: 'ApiaryPage', route: 'apiary', subroute: 'entities', from: 'createCrest' },
            { name: 'Lista de Crestas', component: 'ApiaryPage', route: 'crest', subroute: 'entities', from: 'listCrest' },
            { name: 'Realizar um Desdobramento', component: 'ApiaryPage', route: 'apiary', subroute: 'entities', from: 'createUnfolding' },
            { name: 'Lista de Desdobramentos', component: 'ApiaryPage', route: 'unfolding', subroute: 'entities' },
            { name: 'Pedido de Transumância', component: 'ApiaryPage', route: 'transhumance-request/new', subroute: 'entities' },
            { name: 'Lista de Pedidos de Transumância', component: 'ApiaryPage', route: 'transhumance-request', subroute: 'entities' },
            { name: 'Submissão da declaração anual de existências', component: 'ApiaryPage', route: 'annual-inventory-declaration/new', subroute: 'entities' },
            { name: 'Lista de declarações anuais de existências', component: 'ApiaryPage', route: 'annual-inventory-declaration', subroute: 'entities' },
            { name: 'Adicionar uma Zona', component: 'ApiaryPage', route: 'zone/new', subroute: 'entities' },
        ];
    }
    openPage(page) {
        let navigationExtras;
        if (page.from) {
            navigationExtras = { state: { from: page.from }, queryParams: { from: page.from } };
        }
        this.navController.navigateForward('/tabs/' + page.subroute + '/' + page.route, navigationExtras);
    }
};
EntitiesPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_2__.NavController }
];
EntitiesPage = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Component)({
        selector: 'app-entities',
        template: _entities_page_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
        styles: [_entities_page_scss_ngResource__WEBPACK_IMPORTED_MODULE_1__]
    })
], EntitiesPage);



/***/ }),

/***/ 50537:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/entities.page.scss?ngResource ***!
  \**************************************************************/
/***/ ((module) => {

module.exports = ".trash {\n  position: absolute;\n  margin-left: 205px;\n  margin-top: -125px;\n  color: red;\n  font-size: 16px;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVudGl0aWVzLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGtCQUFBO0VBQ0Esa0JBQUE7RUFDQSxrQkFBQTtFQUNBLFVBQUE7RUFDQSxlQUFBO0FBQ0YiLCJmaWxlIjoiZW50aXRpZXMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnRyYXNoIHtcclxuICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgbWFyZ2luLWxlZnQ6IDIwNXB4O1xyXG4gIG1hcmdpbi10b3A6IC0xMjVweDtcclxuICBjb2xvcjogcmVkO1xyXG4gIGZvbnQtc2l6ZTogMTZweDtcclxufVxyXG4iXX0= */";

/***/ }),

/***/ 94668:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/entities.page.html?ngResource ***!
  \**************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-title> Menu </ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item *ngFor=\"let entity of entities\" (click)=\"openPage(entity)\">\r\n      <h2>{{entity.name}}</h2>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <div *ngIf=\"entities.length === 0\">{{ 'EMPTY_ENTITIES_MESSAGE' | translate }}</div>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_entities_module_ts.js.map