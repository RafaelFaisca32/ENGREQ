"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["default-src_app_pages_entities_zone_index_ts"],{

/***/ 88434:
/*!**********************************************!*\
  !*** ./src/app/pages/entities/zone/index.ts ***!
  \**********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Zone": () => (/* reexport safe */ _zone_model__WEBPACK_IMPORTED_MODULE_0__.Zone),
/* harmony export */   "ZoneDetailPage": () => (/* reexport safe */ _zone_detail__WEBPACK_IMPORTED_MODULE_2__.ZoneDetailPage),
/* harmony export */   "ZonePage": () => (/* reexport safe */ _zone__WEBPACK_IMPORTED_MODULE_3__.ZonePage),
/* harmony export */   "ZoneService": () => (/* reexport safe */ _zone_service__WEBPACK_IMPORTED_MODULE_1__.ZoneService)
/* harmony export */ });
/* harmony import */ var _zone_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./zone.model */ 79848);
/* harmony import */ var _zone_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./zone.service */ 10347);
/* harmony import */ var _zone_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./zone-detail */ 3360);
/* harmony import */ var _zone__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./zone */ 9927);






/***/ }),

/***/ 3360:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/zone/zone-detail.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ZoneDetailPage": () => (/* binding */ ZoneDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _zone_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./zone-detail.html?ngResource */ 80187);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _zone_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./zone.service */ 10347);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let ZoneDetailPage = class ZoneDetailPage {
    constructor(navController, zoneService, activatedRoute, alertController) {
        this.navController = navController;
        this.zoneService = zoneService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.zone = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.zone = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/zone/' + item.id + '/edit');
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
                            this.zoneService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/zone');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
ZoneDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _zone_service__WEBPACK_IMPORTED_MODULE_1__.ZoneService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
ZoneDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-zone-detail',
        template: _zone_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ZoneDetailPage);



/***/ }),

/***/ 79848:
/*!***************************************************!*\
  !*** ./src/app/pages/entities/zone/zone.model.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Zone": () => (/* binding */ Zone)
/* harmony export */ });
class Zone {
    constructor(id, name, number, coordX, coordY, coordZ, controlledZone, apiaryId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
        this.controlledZone = controlledZone;
        this.apiaryId = apiaryId;
        this.controlledZone = false;
    }
}


/***/ }),

/***/ 10347:
/*!*****************************************************!*\
  !*** ./src/app/pages/entities/zone/zone.service.ts ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ZoneService": () => (/* binding */ ZoneService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let ZoneService = class ZoneService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/zones';
    }
    create(zone) {
        return this.http.post(this.resourceUrl, zone, { observe: 'response' });
    }
    update(zone) {
        return this.http.put(`${this.resourceUrl}/${zone.id}`, zone, { observe: 'response' });
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
ZoneService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
ZoneService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], ZoneService);



/***/ }),

/***/ 9927:
/*!*********************************************!*\
  !*** ./src/app/pages/entities/zone/zone.ts ***!
  \*********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ZonePage": () => (/* binding */ ZonePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _zone_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./zone.html?ngResource */ 7636);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _zone_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./zone.service */ 10347);






let ZonePage = class ZonePage {
    // todo: add pagination
    constructor(navController, zoneService, toastCtrl, plt) {
        this.navController = navController;
        this.zoneService = zoneService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.zones = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.zoneService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.zones = response;
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
            yield this.navController.navigateForward('/tabs/entities/zone/new');
        });
    }
    edit(item, zone) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/zone/' + zone.id + '/edit');
            yield item.close();
        });
    }
    delete(zone) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.zoneService.delete(zone.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Zone deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(zone) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/zone/' + zone.id + '/view');
        });
    }
};
ZonePage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _zone_service__WEBPACK_IMPORTED_MODULE_1__.ZoneService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
ZonePage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-zone',
        template: _zone_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ZonePage);



/***/ }),

/***/ 80187:
/*!*****************************************************************!*\
  !*** ./src/app/pages/entities/zone/zone-detail.html?ngResource ***!
  \*****************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Zone</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{zone.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"name-content\">{{zone.name}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"number-content\">{{zone.number}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord X</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordX-content\">{{zone.coordX}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord Y</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordY-content\">{{zone.coordY}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord Z</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordZ-content\">{{zone.coordZ}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Controlled Zone</ion-label>\r\n      <div item-content>\r\n        <span id=\"controlledZone-content\">{{zone.controlledZone}}</span>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(zone)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(zone)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 7636:
/*!**********************************************************!*\
  !*** ./src/app/pages/entities/zone/zone.html?ngResource ***!
  \**********************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Zones</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let zone of zones; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(zone)\">\r\n        <ion-label text-wrap>\r\n          <p>{{zone.id}}</p>\r\n          <ion-text color=\"primary\"><h2>{{zone.name}}</h2></ion-text>\r\n          <p>{{zone.number}}</p>\r\n          <p>{{zone.coordX}}</p>\r\n          <p>{{zone.coordY}}</p>\r\n          <p>{{zone.coordZ}}</p>\r\n          <p>{{zone.controlledZone}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, zone)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(zone)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!zones?.length\">\r\n    <ion-label> No Zones found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=default-src_app_pages_entities_zone_index_ts.js.map