"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["default-src_app_pages_entities_hive_index_ts-src_app_pages_entities_transhumance-request_index_ts"],{

/***/ 29472:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/hive/hive-detail.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "HiveDetailPage": () => (/* binding */ HiveDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _hive_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./hive-detail.html?ngResource */ 86293);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _hive_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./hive.service */ 54232);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let HiveDetailPage = class HiveDetailPage {
    constructor(navController, hiveService, activatedRoute, alertController) {
        this.navController = navController;
        this.hiveService = hiveService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.hive = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.hive = response.data;
            this.hive.apiaryId = this.hive.apiary.id;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/hive/' + item.id + '/edit');
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
                            this.hiveService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/hive');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
HiveDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _hive_service__WEBPACK_IMPORTED_MODULE_1__.HiveService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
HiveDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-hive-detail',
        template: _hive_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], HiveDetailPage);



/***/ }),

/***/ 82144:
/*!***************************************************!*\
  !*** ./src/app/pages/entities/hive/hive.model.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Hive": () => (/* binding */ Hive)
/* harmony export */ });
class Hive {
    constructor(id, code, apiaryundefined, apiaryId, apiary, crests, unfoldings, frames, inspections, transhumanceRequests) {
        this.id = id;
        this.code = code;
        this.apiaryundefined = apiaryundefined;
        this.apiaryId = apiaryId;
        this.apiary = apiary;
        this.crests = crests;
        this.unfoldings = unfoldings;
        this.frames = frames;
        this.inspections = inspections;
        this.transhumanceRequests = transhumanceRequests;
    }
}


/***/ }),

/***/ 83044:
/*!*********************************************!*\
  !*** ./src/app/pages/entities/hive/hive.ts ***!
  \*********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "HivePage": () => (/* binding */ HivePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _hive_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./hive.html?ngResource */ 96318);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _hive_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./hive.service */ 54232);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);







let HivePage = class HivePage {
    // todo: add pagination
    constructor(navController, hiveService, toastCtrl, plt, route, router) {
        this.navController = navController;
        this.hiveService = hiveService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.route = route;
        this.router = router;
        this.hives = [];
        this.route.queryParams.subscribe(params => {
            this.from = params.from;
            this.apiaryId = params.apiaryId;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            let queryParams = {};
            if (!!this.apiaryId) {
                queryParams = { 'apiaryId.equals': this.apiaryId };
            }
            this.hiveService
                .query(queryParams)
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.hives = response;
                for (let i = 0; i < this.hives.length; i++) {
                    this.hives[i].apiaryId = this.hives[i].apiary.id;
                }
                if (typeof refresher !== 'undefined') {
                    setTimeout(() => {
                        refresher.target.complete();
                    }, 750);
                }
            }, (error) => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
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
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/hive/new');
        });
    }
    edit(item, hive) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/hive/' + hive.id + '/edit');
            yield item.close();
        });
    }
    delete(hive) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.hiveService.delete(hive.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Hive deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(hive) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/hive/' + hive.id + '/view');
        });
    }
};
HivePage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _hive_service__WEBPACK_IMPORTED_MODULE_1__.HiveService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.Router }
];
HivePage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_7__.Component)({
        selector: 'page-hive',
        template: _hive_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], HivePage);



/***/ }),

/***/ 45734:
/*!**********************************************!*\
  !*** ./src/app/pages/entities/hive/index.ts ***!
  \**********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Hive": () => (/* reexport safe */ _hive_model__WEBPACK_IMPORTED_MODULE_0__.Hive),
/* harmony export */   "HiveDetailPage": () => (/* reexport safe */ _hive_detail__WEBPACK_IMPORTED_MODULE_2__.HiveDetailPage),
/* harmony export */   "HivePage": () => (/* reexport safe */ _hive__WEBPACK_IMPORTED_MODULE_3__.HivePage),
/* harmony export */   "HiveService": () => (/* reexport safe */ _hive_service__WEBPACK_IMPORTED_MODULE_1__.HiveService)
/* harmony export */ });
/* harmony import */ var _hive_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./hive.model */ 82144);
/* harmony import */ var _hive_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./hive.service */ 54232);
/* harmony import */ var _hive_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./hive-detail */ 29472);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./hive */ 83044);






/***/ }),

/***/ 79524:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/index.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequest": () => (/* reexport safe */ _transhumance_request_model__WEBPACK_IMPORTED_MODULE_0__.TranshumanceRequest),
/* harmony export */   "TranshumanceRequestDetailPage": () => (/* reexport safe */ _transhumance_request_detail__WEBPACK_IMPORTED_MODULE_2__.TranshumanceRequestDetailPage),
/* harmony export */   "TranshumanceRequestPage": () => (/* reexport safe */ _transhumance_request__WEBPACK_IMPORTED_MODULE_3__.TranshumanceRequestPage),
/* harmony export */   "TranshumanceRequestService": () => (/* reexport safe */ _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequestService)
/* harmony export */ });
/* harmony import */ var _transhumance_request_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./transhumance-request.model */ 48559);
/* harmony import */ var _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./transhumance-request.service */ 91296);
/* harmony import */ var _transhumance_request_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./transhumance-request-detail */ 74184);
/* harmony import */ var _transhumance_request__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./transhumance-request */ 64911);






/***/ }),

/***/ 74184:
/*!************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request-detail.ts ***!
  \************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequestDetailPage": () => (/* binding */ TranshumanceRequestDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _transhumance_request_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./transhumance-request-detail.html?ngResource */ 90152);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./transhumance-request.service */ 91296);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let TranshumanceRequestDetailPage = class TranshumanceRequestDetailPage {
    constructor(navController, transhumanceRequestService, activatedRoute, alertController) {
        this.navController = navController;
        this.transhumanceRequestService = transhumanceRequestService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.transhumanceRequest = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.transhumanceRequest = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/transhumance-request/' + item.id + '/edit');
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
                            this.transhumanceRequestService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/transhumance-request');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
TranshumanceRequestDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequestService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
TranshumanceRequestDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-transhumance-request-detail',
        template: _transhumance_request_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], TranshumanceRequestDetailPage);



/***/ }),

/***/ 48559:
/*!***********************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request.model.ts ***!
  \***********************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequest": () => (/* binding */ TranshumanceRequest)
/* harmony export */ });
class TranshumanceRequest {
    constructor(id, requestDate, state, apiaryundefined, destinationApiaryId, apiaryId, hives) {
        this.id = id;
        this.requestDate = requestDate;
        this.state = state;
        this.apiaryundefined = apiaryundefined;
        this.destinationApiaryId = destinationApiaryId;
        this.apiaryId = apiaryId;
        this.hives = hives;
    }
}


/***/ }),

/***/ 91296:
/*!*************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request.service.ts ***!
  \*************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequestService": () => (/* binding */ TranshumanceRequestService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let TranshumanceRequestService = class TranshumanceRequestService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/transhumance-requests';
    }
    create(transhumanceRequest) {
        return this.http.post(this.resourceUrl, transhumanceRequest, { observe: 'response' });
    }
    update(transhumanceRequest) {
        return this.http.put(`${this.resourceUrl}/${transhumanceRequest.id}`, transhumanceRequest, { observe: 'response' });
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
TranshumanceRequestService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
TranshumanceRequestService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], TranshumanceRequestService);



/***/ }),

/***/ 64911:
/*!*****************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request.ts ***!
  \*****************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequestPage": () => (/* binding */ TranshumanceRequestPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _transhumance_request_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./transhumance-request.html?ngResource */ 18124);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./transhumance-request.service */ 91296);






let TranshumanceRequestPage = class TranshumanceRequestPage {
    // todo: add pagination
    constructor(navController, transhumanceRequestService, toastCtrl, plt) {
        this.navController = navController;
        this.transhumanceRequestService = transhumanceRequestService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.transhumanceRequests = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.transhumanceRequestService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.transhumanceRequests = response;
                if (typeof refresher !== 'undefined') {
                    setTimeout(() => {
                        refresher.target.complete();
                    }, 750);
                }
            }, (error) => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
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
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/transhumance-request/new');
        });
    }
    edit(item, transhumanceRequest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/transhumance-request/' + transhumanceRequest.id + '/edit');
            yield item.close();
        });
    }
    delete(transhumanceRequest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.transhumanceRequestService.delete(transhumanceRequest.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({
                    message: 'TranshumanceRequest deleted successfully.',
                    duration: 3000,
                    position: 'middle',
                });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(transhumanceRequest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/transhumance-request/' + transhumanceRequest.id + '/view');
        });
    }
};
TranshumanceRequestPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _transhumance_request_service__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequestService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
TranshumanceRequestPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-transhumance-request',
        template: _transhumance_request_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], TranshumanceRequestPage);



/***/ }),

/***/ 86293:
/*!*****************************************************************!*\
  !*** ./src/app/pages/entities/hive/hive-detail.html?ngResource ***!
  \*****************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Hive</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{hive.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Code</ion-label>\r\n      <div item-content>\r\n        <span id=\"code-content\">{{hive.code}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Apiary</ion-label>\r\n      <div item-content *ngIf=\"hive.apiaryId\">\r\n        <a>{{hive.apiaryId}}</a>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(hive)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(hive)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 96318:
/*!**********************************************************!*\
  !*** ./src/app/pages/entities/hive/hive.html?ngResource ***!
  \**********************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Hives</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let hive of hives; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(hive)\">\r\n        <ion-label text-wrap>\r\n          <p>{{hive.id}}</p>\r\n          <p>{{hive.code}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, hive)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(hive)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!hives?.length\">\r\n    <ion-label> No Hives found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ }),

/***/ 90152:
/*!*************************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request-detail.html?ngResource ***!
  \*************************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Pedido de transumância</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{transhumanceRequest.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Request Date</ion-label>\r\n      <div item-content>\r\n        <span id=\"requestDate-content\">{{transhumanceRequest.requestDate | date:'medium'}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">State</ion-label>\r\n      <div item-content>\r\n        <span id=\"state-content\">{{transhumanceRequest.state}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Apiary</ion-label>\r\n      <div item-content *ngIf=\"transhumanceRequest.apiaryId\">\r\n        <a>{{transhumanceRequest.apiaryId}}</a>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Hive</ion-label>\r\n      <div item-content>\r\n        <span *ngFor=\"let hive of transhumanceRequest.hives; let last = last\"> <a>{{hive.id}}</a>{{last ? '' : ', '}} </span>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n   <!--<ion-button expand=\"block\" color=\"primary\" (click)=\"open(transhumanceRequest)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>-->\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(transhumanceRequest)\">{{ 'DELETE_BUTTON' | translate }}</ion-button> \r\n</ion-content>\r\n";

/***/ }),

/***/ 18124:
/*!******************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request.html?ngResource ***!
  \******************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Lista de pedidos de Transumância</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let transhumanceRequest of transhumanceRequests; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(transhumanceRequest)\">\r\n        <ion-label text-wrap>\r\n          <p>{{transhumanceRequest.id}}</p>\r\n          <p>{{transhumanceRequest.requestDate | date:'medium'}}</p>\r\n          <!-- todo: special handling for translating enum - {{'TranshumanceRequestState.' + transhumanceRequest.state}}\" -->\r\n          <p>{{transhumanceRequest.state}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, transhumanceRequest)\">\r\n          {{ 'EDIT_BUTTON' | translate }}\r\n        </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(transhumanceRequest)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!transhumanceRequests?.length\">\r\n    <ion-label> No Transhumance Requests found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=default-src_app_pages_entities_hive_index_ts-src_app_pages_entities_transhumance-request_index_ts.js.map