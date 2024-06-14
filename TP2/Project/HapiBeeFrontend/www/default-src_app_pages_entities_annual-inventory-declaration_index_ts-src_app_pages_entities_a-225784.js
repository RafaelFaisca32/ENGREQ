"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["default-src_app_pages_entities_annual-inventory-declaration_index_ts-src_app_pages_entities_a-225784"],{

/***/ 83067:
/*!****************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration-detail.ts ***!
  \****************************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclarationDetailPage": () => (/* binding */ AnnualInventoryDeclarationDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _annual_inventory_declaration_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./annual-inventory-declaration-detail.html?ngResource */ 97936);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./annual-inventory-declaration.service */ 21619);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let AnnualInventoryDeclarationDetailPage = class AnnualInventoryDeclarationDetailPage {
    constructor(navController, annualInventoryDeclarationService, activatedRoute, alertController) {
        this.navController = navController;
        this.annualInventoryDeclarationService = annualInventoryDeclarationService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.annualInventoryDeclaration = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.annualInventoryDeclaration = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + item.id + '/edit');
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
                            this.annualInventoryDeclarationService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/annual-inventory-declaration');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
AnnualInventoryDeclarationDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclarationService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
AnnualInventoryDeclarationDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-annual-inventory-declaration-detail',
        template: _annual_inventory_declaration_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], AnnualInventoryDeclarationDetailPage);



/***/ }),

/***/ 66937:
/*!***************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration.model.ts ***!
  \***************************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclaration": () => (/* binding */ AnnualInventoryDeclaration)
/* harmony export */ });
class AnnualInventoryDeclaration {
    constructor(id, total, beekeeperFaxNumber, beekeeperCompleteName, beekeeperNif, date, beekeeperAddress, beekeeperPostalCode, beekeeperPhoneNumber, beekeeperEntityZoneResidence, beekeeperNumber, annualInventoryDeclarationState, revisionDate, revisionLocation, revisorSignature, revisorName, apiaryInformations) {
        this.id = id;
        this.total = total;
        this.beekeeperFaxNumber = beekeeperFaxNumber;
        this.beekeeperCompleteName = beekeeperCompleteName;
        this.beekeeperNif = beekeeperNif;
        this.date = date;
        this.beekeeperAddress = beekeeperAddress;
        this.beekeeperPostalCode = beekeeperPostalCode;
        this.beekeeperPhoneNumber = beekeeperPhoneNumber;
        this.beekeeperEntityZoneResidence = beekeeperEntityZoneResidence;
        this.beekeeperNumber = beekeeperNumber;
        this.annualInventoryDeclarationState = annualInventoryDeclarationState;
        this.revisionDate = revisionDate;
        this.revisionLocation = revisionLocation;
        this.revisorSignature = revisorSignature;
        this.revisorName = revisorName;
        this.apiaryInformations = apiaryInformations;
    }
}


/***/ }),

/***/ 21619:
/*!*****************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration.service.ts ***!
  \*****************************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclarationService": () => (/* binding */ AnnualInventoryDeclarationService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let AnnualInventoryDeclarationService = class AnnualInventoryDeclarationService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/annual-inventory-declarations';
    }
    create(annualInventoryDeclaration) {
        return this.http.post(this.resourceUrl, annualInventoryDeclaration, { observe: 'response' });
    }
    update(annualInventoryDeclaration) {
        return this.http.put(`${this.resourceUrl}/${annualInventoryDeclaration.id}`, annualInventoryDeclaration, { observe: 'response' });
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
AnnualInventoryDeclarationService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
AnnualInventoryDeclarationService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], AnnualInventoryDeclarationService);



/***/ }),

/***/ 88431:
/*!*********************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration.ts ***!
  \*********************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclarationPage": () => (/* binding */ AnnualInventoryDeclarationPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _annual_inventory_declaration_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./annual-inventory-declaration.html?ngResource */ 36681);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./annual-inventory-declaration.service */ 21619);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../apiary */ 50180);







let AnnualInventoryDeclarationPage = class AnnualInventoryDeclarationPage {
    constructor(apiaryService, navController, annualInventoryDeclarationService, toastCtrl, plt) {
        this.apiaryService = apiaryService;
        this.navController = navController;
        this.annualInventoryDeclarationService = annualInventoryDeclarationService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.annualInventoryDeclarations = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            this.annualInventoryDeclarationService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((res) => res.body))
                .subscribe((response) => {
                this.annualInventoryDeclarations = response;
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
            yield this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/new');
        });
    }
    edit(item, annualInventoryDeclaration) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + annualInventoryDeclaration.id + '/edit');
            yield item.close();
        });
    }
    delete(annualInventoryDeclaration) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            this.annualInventoryDeclarationService.delete(annualInventoryDeclaration.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({
                    message: 'AnnualInventoryDeclaration deleted successfully.',
                    duration: 3000,
                    position: 'middle',
                });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(annualInventoryDeclaration) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + annualInventoryDeclaration.id + '/view');
        });
    }
};
AnnualInventoryDeclarationPage.ctorParameters = () => [
    { type: _apiary__WEBPACK_IMPORTED_MODULE_2__.ApiaryService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.NavController },
    { type: _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclarationService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.Platform }
];
AnnualInventoryDeclarationPage = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_7__.Component)({
        selector: 'page-annual-inventory-declaration',
        template: _annual_inventory_declaration_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], AnnualInventoryDeclarationPage);



/***/ }),

/***/ 11602:
/*!**********************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/index.ts ***!
  \**********************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclaration": () => (/* reexport safe */ _annual_inventory_declaration_model__WEBPACK_IMPORTED_MODULE_0__.AnnualInventoryDeclaration),
/* harmony export */   "AnnualInventoryDeclarationDetailPage": () => (/* reexport safe */ _annual_inventory_declaration_detail__WEBPACK_IMPORTED_MODULE_2__.AnnualInventoryDeclarationDetailPage),
/* harmony export */   "AnnualInventoryDeclarationPage": () => (/* reexport safe */ _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclarationPage),
/* harmony export */   "AnnualInventoryDeclarationService": () => (/* reexport safe */ _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclarationService)
/* harmony export */ });
/* harmony import */ var _annual_inventory_declaration_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./annual-inventory-declaration.model */ 66937);
/* harmony import */ var _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./annual-inventory-declaration.service */ 21619);
/* harmony import */ var _annual_inventory_declaration_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./annual-inventory-declaration-detail */ 83067);
/* harmony import */ var _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./annual-inventory-declaration */ 88431);






/***/ }),

/***/ 67027:
/*!********************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information-detail.ts ***!
  \********************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformationDetailPage": () => (/* binding */ ApiaryInformationDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_information_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-information-detail.html?ngResource */ 76511);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary-information.service */ 70429);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let ApiaryInformationDetailPage = class ApiaryInformationDetailPage {
    constructor(navController, apiaryInformationService, activatedRoute, alertController) {
        this.navController = navController;
        this.apiaryInformationService = apiaryInformationService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.apiaryInformation = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.apiaryInformation = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/apiary-information/' + item.id + '/edit');
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
                            this.apiaryInformationService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/apiary-information');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
ApiaryInformationDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformationService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
ApiaryInformationDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-apiary-information-detail',
        template: _apiary_information_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryInformationDetailPage);



/***/ }),

/***/ 86514:
/*!*******************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information.model.ts ***!
  \*******************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformation": () => (/* binding */ ApiaryInformation)
/* harmony export */ });
class ApiaryInformation {
    constructor(id, zoneNumber, zoneName, numberHives, intensive, transhumance, coordX, coordY, coordZ, numberFrames, annualInventoryDeclarationundefined, annualInventoryDeclarationId) {
        this.id = id;
        this.zoneNumber = zoneNumber;
        this.zoneName = zoneName;
        this.numberHives = numberHives;
        this.intensive = intensive;
        this.transhumance = transhumance;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
        this.numberFrames = numberFrames;
        this.annualInventoryDeclarationundefined = annualInventoryDeclarationundefined;
        this.annualInventoryDeclarationId = annualInventoryDeclarationId;
        this.intensive = false;
        this.transhumance = false;
    }
}


/***/ }),

/***/ 70429:
/*!*********************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information.service.ts ***!
  \*********************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformationService": () => (/* binding */ ApiaryInformationService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let ApiaryInformationService = class ApiaryInformationService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/apiary-informations';
    }
    create(apiaryInformation) {
        return this.http.post(this.resourceUrl, apiaryInformation, { observe: 'response' });
    }
    update(apiaryInformation) {
        return this.http.put(`${this.resourceUrl}/${apiaryInformation.id}`, apiaryInformation, { observe: 'response' });
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
ApiaryInformationService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
ApiaryInformationService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], ApiaryInformationService);



/***/ }),

/***/ 6887:
/*!*************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information.ts ***!
  \*************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformationPage": () => (/* binding */ ApiaryInformationPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_information_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-information.html?ngResource */ 48165);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary-information.service */ 70429);






let ApiaryInformationPage = class ApiaryInformationPage {
    // todo: add pagination
    constructor(navController, apiaryInformationService, toastCtrl, plt) {
        this.navController = navController;
        this.apiaryInformationService = apiaryInformationService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.apiaryInformations = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.apiaryInformationService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.apiaryInformations = response;
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
            yield this.navController.navigateForward('/tabs/entities/apiary-information/new');
        });
    }
    edit(item, apiaryInformation) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/apiary-information/' + apiaryInformation.id + '/edit');
            yield item.close();
        });
    }
    delete(apiaryInformation) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.apiaryInformationService.delete(apiaryInformation.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({
                    message: 'ApiaryInformation deleted successfully.',
                    duration: 3000,
                    position: 'middle',
                });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(apiaryInformation) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/apiary-information/' + apiaryInformation.id + '/view');
        });
    }
};
ApiaryInformationPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformationService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
ApiaryInformationPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-apiary-information',
        template: _apiary_information_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryInformationPage);



/***/ }),

/***/ 4525:
/*!************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/index.ts ***!
  \************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformation": () => (/* reexport safe */ _apiary_information_model__WEBPACK_IMPORTED_MODULE_0__.ApiaryInformation),
/* harmony export */   "ApiaryInformationDetailPage": () => (/* reexport safe */ _apiary_information_detail__WEBPACK_IMPORTED_MODULE_2__.ApiaryInformationDetailPage),
/* harmony export */   "ApiaryInformationPage": () => (/* reexport safe */ _apiary_information__WEBPACK_IMPORTED_MODULE_3__.ApiaryInformationPage),
/* harmony export */   "ApiaryInformationService": () => (/* reexport safe */ _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformationService)
/* harmony export */ });
/* harmony import */ var _apiary_information_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-information.model */ 86514);
/* harmony import */ var _apiary_information_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary-information.service */ 70429);
/* harmony import */ var _apiary_information_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary-information-detail */ 67027);
/* harmony import */ var _apiary_information__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./apiary-information */ 6887);






/***/ }),

/***/ 97936:
/*!*****************************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration-detail.html?ngResource ***!
  \*****************************************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Annual Inventory Declaration</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{annualInventoryDeclaration.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Total</ion-label>\r\n      <div item-content>\r\n        <span id=\"total-content\">{{annualInventoryDeclaration.total}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Fax Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperFaxNumber-content\">{{annualInventoryDeclaration.beekeeperFaxNumber}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Complete Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperCompleteName-content\">{{annualInventoryDeclaration.beekeeperCompleteName}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Nif</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperNif-content\">{{annualInventoryDeclaration.beekeeperNif}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Date</ion-label>\r\n      <div item-content>\r\n        <span id=\"date-content\">{{annualInventoryDeclaration.date | date:'mediumDate'}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Address</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperAddress-content\">{{annualInventoryDeclaration.beekeeperAddress}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Postal Code</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperPostalCode-content\">{{annualInventoryDeclaration.beekeeperPostalCode}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Phone Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperPhoneNumber-content\">{{annualInventoryDeclaration.beekeeperPhoneNumber}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Entity Zone Residence</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperEntityZoneResidence-content\">{{annualInventoryDeclaration.beekeeperEntityZoneResidence}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Beekeeper Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"beekeeperNumber-content\">{{annualInventoryDeclaration.beekeeperNumber}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Annual Inventory Declaration State</ion-label>\r\n      <div item-content>\r\n        <span id=\"annualInventoryDeclarationState-content\">{{annualInventoryDeclaration.annualInventoryDeclarationState}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Revision Date</ion-label>\r\n      <div item-content>\r\n        <span id=\"revisionDate-content\">{{annualInventoryDeclaration.revisionDate | date:'mediumDate'}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Revision Location</ion-label>\r\n      <div item-content>\r\n        <span id=\"revisionLocation-content\">{{annualInventoryDeclaration.revisionLocation}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Revisor Signature</ion-label>\r\n      <div item-content>\r\n        <span id=\"revisorSignature-content\">{{annualInventoryDeclaration.revisorSignature}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Revisor Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"revisorName-content\">{{annualInventoryDeclaration.revisorName}}</span>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(annualInventoryDeclaration)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(annualInventoryDeclaration)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 36681:
/*!**********************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration.html?ngResource ***!
  \**********************************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Annual Inventory Declarations</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let annualInventoryDeclaration of annualInventoryDeclarations; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(annualInventoryDeclaration)\">\r\n        <ion-label text-wrap>\r\n          <p>{{annualInventoryDeclaration.id}}</p>\r\n          <p>{{annualInventoryDeclaration.total}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperFaxNumber}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperCompleteName}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperNif}}</p>\r\n          <p>{{annualInventoryDeclaration.date | date:'mediumDate'}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperAddress}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperPostalCode}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperPhoneNumber}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperEntityZoneResidence}}</p>\r\n          <p>{{annualInventoryDeclaration.beekeeperNumber}}</p>\r\n          <!-- todo: special handling for translating enum - {{'AnnualInventoyDeclarationState.' + annualInventoryDeclaration.annualInventoryDeclarationState}}\" -->\r\n          <p>{{annualInventoryDeclaration.annualInventoryDeclarationState}}</p>\r\n          <p>{{annualInventoryDeclaration.revisionDate | date:'mediumDate'}}</p>\r\n          <p>{{annualInventoryDeclaration.revisionLocation}}</p>\r\n          <p>{{annualInventoryDeclaration.revisorSignature}}</p>\r\n          <p>{{annualInventoryDeclaration.revisorName}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, annualInventoryDeclaration)\">\r\n          {{ 'EDIT_BUTTON' | translate }}\r\n        </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(annualInventoryDeclaration)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!annualInventoryDeclarations?.length\">\r\n    <ion-label> No Annual Inventory Declarations found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ }),

/***/ 76511:
/*!*********************************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information-detail.html?ngResource ***!
  \*********************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Apiary Information</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{apiaryInformation.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Zone Number</ion-label>\r\n      <div item-content>\r\n        <span id=\"zoneNumber-content\">{{apiaryInformation.zoneNumber}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Zone Name</ion-label>\r\n      <div item-content>\r\n        <span id=\"zoneName-content\">{{apiaryInformation.zoneName}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Number Hives</ion-label>\r\n      <div item-content>\r\n        <span id=\"numberHives-content\">{{apiaryInformation.numberHives}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Intensive</ion-label>\r\n      <div item-content>\r\n        <span id=\"intensive-content\">{{apiaryInformation.intensive}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Transhumance</ion-label>\r\n      <div item-content>\r\n        <span id=\"transhumance-content\">{{apiaryInformation.transhumance}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord X</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordX-content\">{{apiaryInformation.coordX}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord Y</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordY-content\">{{apiaryInformation.coordY}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Coord Z</ion-label>\r\n      <div item-content>\r\n        <span id=\"coordZ-content\">{{apiaryInformation.coordZ}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Number Frames</ion-label>\r\n      <div item-content>\r\n        <span id=\"numberFrames-content\">{{apiaryInformation.numberFrames}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Annual Inventory Declaration</ion-label>\r\n      <div item-content *ngIf=\"apiaryInformation.annualInventoryDeclarationId\">\r\n        <a>{{apiaryInformation.annualInventoryDeclarationundefined}}</a>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(apiaryInformation)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(apiaryInformation)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 48165:
/*!**************************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information.html?ngResource ***!
  \**************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Apiary Informations</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let apiaryInformation of apiaryInformations; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(apiaryInformation)\">\r\n        <ion-label text-wrap>\r\n          <p>{{apiaryInformation.id}}</p>\r\n          <p>{{apiaryInformation.zoneNumber}}</p>\r\n          <p>{{apiaryInformation.zoneName}}</p>\r\n          <p>{{apiaryInformation.numberHives}}</p>\r\n          <p>{{apiaryInformation.intensive}}</p>\r\n          <p>{{apiaryInformation.transhumance}}</p>\r\n          <p>{{apiaryInformation.coordX}}</p>\r\n          <p>{{apiaryInformation.coordY}}</p>\r\n          <p>{{apiaryInformation.coordZ}}</p>\r\n          <p>{{apiaryInformation.numberFrames}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, apiaryInformation)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(apiaryInformation)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!apiaryInformations?.length\">\r\n    <ion-label> No Apiary Informations found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=default-src_app_pages_entities_annual-inventory-declaration_index_ts-src_app_pages_entities_a-225784.js.map