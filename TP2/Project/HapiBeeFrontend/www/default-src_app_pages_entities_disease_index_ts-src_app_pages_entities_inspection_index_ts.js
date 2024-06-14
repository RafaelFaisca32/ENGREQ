"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["default-src_app_pages_entities_disease_index_ts-src_app_pages_entities_inspection_index_ts"],{

/***/ 44086:
/*!**********************************************************!*\
  !*** ./src/app/pages/entities/disease/disease-detail.ts ***!
  \**********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "DiseaseDetailPage": () => (/* binding */ DiseaseDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _disease_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./disease-detail.html?ngResource */ 40092);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _disease_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./disease.service */ 33082);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let DiseaseDetailPage = class DiseaseDetailPage {
    constructor(navController, diseaseService, activatedRoute, alertController) {
        this.navController = navController;
        this.diseaseService = diseaseService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.disease = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.disease = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/disease/' + item.id + '/edit');
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
                            this.diseaseService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/disease');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
DiseaseDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _disease_service__WEBPACK_IMPORTED_MODULE_1__.DiseaseService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
DiseaseDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-disease-detail',
        template: _disease_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], DiseaseDetailPage);



/***/ }),

/***/ 90600:
/*!*********************************************************!*\
  !*** ./src/app/pages/entities/disease/disease.model.ts ***!
  \*********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Disease": () => (/* binding */ Disease)
/* harmony export */ });
class Disease {
    constructor(id, name, description, inspections) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inspections = inspections;
    }
}


/***/ }),

/***/ 33082:
/*!***********************************************************!*\
  !*** ./src/app/pages/entities/disease/disease.service.ts ***!
  \***********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "DiseaseService": () => (/* binding */ DiseaseService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let DiseaseService = class DiseaseService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/diseases';
    }
    create(disease) {
        return this.http.post(this.resourceUrl, disease, { observe: 'response' });
    }
    update(disease) {
        return this.http.put(`${this.resourceUrl}/${disease.id}`, disease, { observe: 'response' });
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
DiseaseService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
DiseaseService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], DiseaseService);



/***/ }),

/***/ 50701:
/*!***************************************************!*\
  !*** ./src/app/pages/entities/disease/disease.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "DiseasePage": () => (/* binding */ DiseasePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _disease_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./disease.html?ngResource */ 25083);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _disease_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./disease.service */ 33082);






let DiseasePage = class DiseasePage {
    // todo: add pagination
    constructor(navController, diseaseService, toastCtrl, plt) {
        this.navController = navController;
        this.diseaseService = diseaseService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.diseases = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.diseaseService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.diseases = response;
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
            yield this.navController.navigateForward('/tabs/entities/disease/new');
        });
    }
    edit(item, disease) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/disease/' + disease.id + '/edit');
            yield item.close();
        });
    }
    delete(disease) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.diseaseService.delete(disease.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Disease deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(disease) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/disease/' + disease.id + '/view');
        });
    }
};
DiseasePage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _disease_service__WEBPACK_IMPORTED_MODULE_1__.DiseaseService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
DiseasePage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-disease',
        template: _disease_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], DiseasePage);



/***/ }),

/***/ 16828:
/*!*************************************************!*\
  !*** ./src/app/pages/entities/disease/index.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Disease": () => (/* reexport safe */ _disease_model__WEBPACK_IMPORTED_MODULE_0__.Disease),
/* harmony export */   "DiseaseDetailPage": () => (/* reexport safe */ _disease_detail__WEBPACK_IMPORTED_MODULE_2__.DiseaseDetailPage),
/* harmony export */   "DiseasePage": () => (/* reexport safe */ _disease__WEBPACK_IMPORTED_MODULE_3__.DiseasePage),
/* harmony export */   "DiseaseService": () => (/* reexport safe */ _disease_service__WEBPACK_IMPORTED_MODULE_1__.DiseaseService)
/* harmony export */ });
/* harmony import */ var _disease_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./disease.model */ 90600);
/* harmony import */ var _disease_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./disease.service */ 33082);
/* harmony import */ var _disease_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./disease-detail */ 44086);
/* harmony import */ var _disease__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./disease */ 50701);






/***/ }),

/***/ 14987:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/inspection/index.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Inspection": () => (/* reexport safe */ _inspection_model__WEBPACK_IMPORTED_MODULE_0__.Inspection),
/* harmony export */   "InspectionDetailPage": () => (/* reexport safe */ _inspection_detail__WEBPACK_IMPORTED_MODULE_2__.InspectionDetailPage),
/* harmony export */   "InspectionPage": () => (/* reexport safe */ _inspection__WEBPACK_IMPORTED_MODULE_3__.InspectionPage),
/* harmony export */   "InspectionService": () => (/* reexport safe */ _inspection_service__WEBPACK_IMPORTED_MODULE_1__.InspectionService)
/* harmony export */ });
/* harmony import */ var _inspection_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./inspection.model */ 62977);
/* harmony import */ var _inspection_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./inspection.service */ 79605);
/* harmony import */ var _inspection_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./inspection-detail */ 86255);
/* harmony import */ var _inspection__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./inspection */ 97544);






/***/ }),

/***/ 86255:
/*!****************************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection-detail.ts ***!
  \****************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "InspectionDetailPage": () => (/* binding */ InspectionDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _inspection_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./inspection-detail.html?ngResource */ 9436);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _inspection_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./inspection.service */ 79605);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let InspectionDetailPage = class InspectionDetailPage {
    constructor(navController, inspectionService, activatedRoute, alertController) {
        this.navController = navController;
        this.inspectionService = inspectionService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.inspection = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.inspection = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/inspection/' + item.id + '/edit');
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
                            this.inspectionService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/inspection');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
InspectionDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _inspection_service__WEBPACK_IMPORTED_MODULE_1__.InspectionService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
InspectionDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-inspection-detail',
        template: _inspection_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], InspectionDetailPage);



/***/ }),

/***/ 62977:
/*!***************************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection.model.ts ***!
  \***************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Inspection": () => (/* binding */ Inspection)
/* harmony export */ });
class Inspection {
    constructor(id, date, note, hiveundefined, hiveId, diseases) {
        this.id = id;
        this.date = date;
        this.note = note;
        this.hiveundefined = hiveundefined;
        this.hiveId = hiveId;
        this.diseases = diseases;
    }
}


/***/ }),

/***/ 79605:
/*!*****************************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection.service.ts ***!
  \*****************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "InspectionService": () => (/* binding */ InspectionService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let InspectionService = class InspectionService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/inspections';
    }
    create(inspection) {
        return this.http.post(this.resourceUrl, inspection, { observe: 'response' });
    }
    update(inspection) {
        return this.http.put(`${this.resourceUrl}/${inspection.id}`, inspection, { observe: 'response' });
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
InspectionService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
InspectionService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], InspectionService);



/***/ }),

/***/ 97544:
/*!*********************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection.ts ***!
  \*********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "InspectionPage": () => (/* binding */ InspectionPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _inspection_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./inspection.html?ngResource */ 4032);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _inspection_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./inspection.service */ 79605);






let InspectionPage = class InspectionPage {
    // todo: add pagination
    constructor(navController, inspectionService, toastCtrl, plt) {
        this.navController = navController;
        this.inspectionService = inspectionService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.inspections = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.inspectionService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.inspections = response;
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
            yield this.navController.navigateForward('/tabs/entities/inspection/new');
        });
    }
    edit(item, inspection) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/inspection/' + inspection.id + '/edit');
            yield item.close();
        });
    }
    delete(inspection) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.inspectionService.delete(inspection.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Inspection deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(inspection) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/inspection/' + inspection.id + '/view');
        });
    }
};
InspectionPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _inspection_service__WEBPACK_IMPORTED_MODULE_1__.InspectionService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
InspectionPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-inspection',
        template: _inspection_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], InspectionPage);



/***/ }),

/***/ 40092:
/*!***********************************************************************!*\
  !*** ./src/app/pages/entities/disease/disease-detail.html?ngResource ***!
  \***********************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Disease</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{disease.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"name-content\">{{disease.name}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Description</ion-label>\r\n      <div item-content>\r\n        <span id=\"description-content\">{{disease.description}}</span>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(disease)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(disease)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 25083:
/*!****************************************************************!*\
  !*** ./src/app/pages/entities/disease/disease.html?ngResource ***!
  \****************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Diseases</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let disease of diseases; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(disease)\">\r\n        <ion-label text-wrap>\r\n          <p>{{disease.id}}</p>\r\n          <ion-text color=\"primary\"><h2>{{disease.name}}</h2></ion-text>\r\n          <p>{{disease.description}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, disease)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(disease)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!diseases?.length\">\r\n    <ion-label> No Diseases found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ }),

/***/ 9436:
/*!*****************************************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection-detail.html?ngResource ***!
  \*****************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Inspection</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{inspection.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Date</ion-label>\r\n      <div item-content>\r\n        <span id=\"date-content\">{{inspection.date | date:'mediumDate'}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Note</ion-label>\r\n      <div item-content>\r\n        <span id=\"note-content\">{{inspection.note}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Hive</ion-label>\r\n      <div item-content *ngIf=\"inspection.hiveId\">\r\n        <a>{{inspection.hiveundefined}}</a>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Disease</ion-label>\r\n      <div item-content>\r\n        <span *ngFor=\"let disease of inspection.diseases; let last = last\"> <a>{{disease.id}}</a>{{last ? '' : ', '}} </span>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(inspection)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(inspection)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 4032:
/*!**********************************************************************!*\
  !*** ./src/app/pages/entities/inspection/inspection.html?ngResource ***!
  \**********************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Inspections</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let inspection of inspections; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(inspection)\">\r\n        <ion-label text-wrap>\r\n          <p>{{inspection.id}}</p>\r\n          <p>{{inspection.date | date:'mediumDate'}}</p>\r\n          <p>{{inspection.note}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, inspection)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(inspection)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!inspections?.length\">\r\n    <ion-label> No Inspections found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=default-src_app_pages_entities_disease_index_ts-src_app_pages_entities_inspection_index_ts.js.map