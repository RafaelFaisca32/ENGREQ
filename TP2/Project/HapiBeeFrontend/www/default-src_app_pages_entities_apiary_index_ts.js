"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["default-src_app_pages_entities_apiary_index_ts"],{

/***/ 1221:
/*!********************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary-detail.ts ***!
  \********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryDetailPage": () => (/* binding */ ApiaryDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-detail.html?ngResource */ 93042);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _apiary_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary.service */ 17586);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let ApiaryDetailPage = class ApiaryDetailPage {
    constructor(navController, apiaryService, activatedRoute, alertController) {
        this.navController = navController;
        this.apiaryService = apiaryService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.apiary = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.apiary = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/apiary/' + item.id + '/edit');
    }
    deleteModal(item) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            const alert = yield this.alertController.create({
                header: 'Confirm the deletion?',
                buttons: [
                    {
                        text: 'Cancel',
                        role: 'cancel',
                        cssClass: 'secondary',
                    },
                    {
                        text: 'Delete',
                        handler: () => {
                            this.apiaryService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/apiary');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
ApiaryDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _apiary_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
ApiaryDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-apiary-detail',
        template: _apiary_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryDetailPage);



/***/ }),

/***/ 23447:
/*!*******************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary.model.ts ***!
  \*******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Apiary": () => (/* binding */ Apiary)
/* harmony export */ });
class Apiary {
    constructor(id, name, state, number, intensive, transhumance, zoneundefined, zoneId, beekeeperundefined, beekeeperId, hives, transhumanceRequests) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.number = number;
        this.intensive = intensive;
        this.transhumance = transhumance;
        this.zoneundefined = zoneundefined;
        this.zoneId = zoneId;
        this.beekeeperundefined = beekeeperundefined;
        this.beekeeperId = beekeeperId;
        this.hives = hives;
        this.transhumanceRequests = transhumanceRequests;
        this.intensive = false;
        this.transhumance = false;
    }
}


/***/ }),

/***/ 17586:
/*!*********************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary.service.ts ***!
  \*********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryService": () => (/* binding */ ApiaryService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let ApiaryService = class ApiaryService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/apiaries';
    }
    create(apiary) {
        return this.http.post(this.resourceUrl, apiary, { observe: 'response' });
    }
    update(apiary) {
        return this.http.put(`${this.resourceUrl}/${apiary.id}`, apiary, { observe: 'response' });
    }
    find(id) {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    query(req) {
        const options = (0,_shared__WEBPACK_IMPORTED_MODULE_1__.createRequestOption)(req);
        return this.http.get(this.resourceUrl, { params: options, observe: 'response' });
    }
    delete(id) {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
};
ApiaryService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
ApiaryService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], ApiaryService);



/***/ }),

/***/ 30267:
/*!*************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryPage": () => (/* binding */ ApiaryPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary.html?ngResource */ 46310);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _apiary_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary.service */ 17586);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _hive_hive_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../hive/hive.service */ 54232);








let ApiaryPage = class ApiaryPage {
    // todo: add pagination
    constructor(navController, apiaryService, hiveService, toastCtrl, plt, route, router) {
        this.navController = navController;
        this.apiaryService = apiaryService;
        this.hiveService = hiveService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.route = route;
        this.router = router;
        this.apiaries = [];
        this.route.queryParams.subscribe(params => {
            this.from = params.from;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            let req;
            let beekeeperId = localStorage.getItem("beekeeperId");
            switch (this.from) {
                case 'createCrest':
                case 'createUnfolding':
                case 'createInspection':
                    req = { 'state.equals': 'APPROVED' };
                    if (!!beekeeperId) {
                        req = { 'state.equals': 'APPROVED',
                            'beekeeperId.equals': beekeeperId };
                    }
                    break;
                default:
                    if (!!beekeeperId) {
                        req = { 'beekeeperId.equals': beekeeperId };
                    }
            }
            this.apiaryService
                .query(req)
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((res) => res.body))
                .subscribe((response) => {
                this.apiaries = [];
                let apiariesAux = response;
                if (this.from == "createUnfolding" || this.from == "createCrest" || this.from == "createInspection") {
                    for (let i = 0; i < apiariesAux.length; i++) {
                        this.hiveService
                            .query({ 'apiaryId.equals': apiariesAux[i].id })
                            .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((res) => res.body))
                            .subscribe((responseHives) => {
                            if (responseHives.length > 0) {
                                this.apiaries.push(apiariesAux[i]);
                            }
                        });
                    }
                }
                else {
                    this.apiaries = apiariesAux;
                }
                if (typeof refresher !== 'undefined') {
                    setTimeout(() => {
                        refresher.target.complete();
                    }, 750);
                }
            }, (error) => (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
                console.error(error);
                const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
                yield toast.present();
            }));
        });
    }
    trackId(index, item) {
        return item.id;
    }
    new() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/apiary/new');
        });
    }
    edit(item, apiary) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/apiary/' + apiary.id + '/edit');
            yield item.close();
        });
    }
    delete(apiary) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            this.apiaryService.delete(apiary.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Apiary deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(apiary) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            let navigationExtras;
            switch (this.from) {
                case 'createCrest':
                    navigationExtras = { state: { apiaryId: apiary.id }, queryParams: { apiaryId: apiary.id } };
                    yield this.navController.navigateForward('/tabs/entities/crest/new', navigationExtras);
                    break;
                case 'createUnfolding':
                    navigationExtras = { state: { apiaryId: apiary.id }, queryParams: { apiaryId: apiary.id, from: 'createUnfolding' } };
                    yield this.navController.navigateForward('/tabs/entities/unfolding/new', navigationExtras);
                    break;
                case 'createInspection':
                    navigationExtras = { state: { apiaryId: apiary.id }, queryParams: { apiaryId: apiary.id, from: 'createUnfolding' } };
                    yield this.navController.navigateForward('/tabs/entities/inspection/new', navigationExtras);
                    break;
                default:
                    yield this.navController.navigateForward('/tabs/entities/apiary/' + apiary.id + '/view');
            }
        });
    }
    isToCreate() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            return this.from == "" || this.from == null || this.from == undefined || this.from == "new";
        });
    }
};
ApiaryPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.NavController },
    { type: _apiary_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryService },
    { type: _hive_hive_service__WEBPACK_IMPORTED_MODULE_2__.HiveService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.Platform },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_7__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_7__.Router }
];
ApiaryPage = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-apiary',
        template: _apiary_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryPage);



/***/ }),

/***/ 50180:
/*!************************************************!*\
  !*** ./src/app/pages/entities/apiary/index.ts ***!
  \************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Apiary": () => (/* reexport safe */ _apiary_model__WEBPACK_IMPORTED_MODULE_0__.Apiary),
/* harmony export */   "ApiaryDetailPage": () => (/* reexport safe */ _apiary_detail__WEBPACK_IMPORTED_MODULE_2__.ApiaryDetailPage),
/* harmony export */   "ApiaryPage": () => (/* reexport safe */ _apiary__WEBPACK_IMPORTED_MODULE_3__.ApiaryPage),
/* harmony export */   "ApiaryService": () => (/* reexport safe */ _apiary_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryService)
/* harmony export */ });
/* harmony import */ var _apiary_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary.model */ 23447);
/* harmony import */ var _apiary_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary.service */ 17586);
/* harmony import */ var _apiary_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary-detail */ 1221);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./apiary */ 30267);






/***/ }),

/***/ 54232:
/*!*****************************************************!*\
  !*** ./src/app/pages/entities/hive/hive.service.ts ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "HiveService": () => (/* binding */ HiveService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let HiveService = class HiveService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/hives';
    }
    create(hive) {
        return this.http.post(this.resourceUrl, hive, { observe: 'response' });
    }
    update(hive) {
        return this.http.put(`${this.resourceUrl}/${hive.id}`, hive, { observe: 'response' });
    }
    find(id) {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    query(req) {
        const options = (0,_shared__WEBPACK_IMPORTED_MODULE_1__.createRequestOption)(req);
        return this.http.get(this.resourceUrl, { params: options, observe: 'response' });
    }
    delete(id) {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
};
HiveService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
HiveService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], HiveService);



/***/ }),

/***/ 93042:
/*!*********************************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary-detail.html?ngResource ***!
  \*********************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Apiário</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{apiary.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"name-content\">{{apiary.name}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">State</ion-label>\r\n      <div item-content>\r\n        <span id=\"state-content\">{{apiary.state}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"number-content\">{{apiary.number}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Intensive</ion-label>\r\n      <div item-content>\r\n        <span id=\"intensive-content\">{{apiary.intensive}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Transhumance</ion-label>\r\n      <div item-content>\r\n        <span id=\"transhumance-content\">{{apiary.transhumance}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Zone</ion-label>\r\n      <div item-content *ngIf=\"apiary.zoneId\">\r\n        <a>{{apiary.zoneundefined}}</a>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Beekeeper</ion-label>\r\n      <div item-content *ngIf=\"apiary.beekeeperId\">\r\n        <a>{{apiary.beekeeperundefined}}</a>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(apiary)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(apiary)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 46310:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary.html?ngResource ***!
  \**************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Lista de Apiários do utilizador</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let apiary of apiaries; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(apiary)\">\r\n        <ion-label text-wrap>\r\n          <p>{{apiary.id}}</p>\r\n          <ion-text color=\"primary\"><h2>{{apiary.name}}</h2></ion-text>\r\n          <!-- todo: special handling for translating enum - {{'ApiaryState.' + apiary.state}}\" -->\r\n          <p>{{apiary.state}}</p>\r\n          <p>{{apiary.number}}</p>\r\n          <p>{{apiary.intensive}}</p>\r\n          <p>{{apiary.transhumance}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, apiary)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(apiary)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!apiaries?.length\">\r\n    <ion-label> Nenhum apiário encontrado. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab *ngIf=\"isToCreate() == true \" vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=default-src_app_pages_entities_apiary_index_ts.js.map