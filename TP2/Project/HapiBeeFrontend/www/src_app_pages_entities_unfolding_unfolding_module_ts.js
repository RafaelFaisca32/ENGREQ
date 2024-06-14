"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_unfolding_unfolding_module_ts"],{

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

/***/ 26386:
/*!***************************************************!*\
  !*** ./src/app/pages/entities/unfolding/index.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Unfolding": () => (/* reexport safe */ _unfolding_model__WEBPACK_IMPORTED_MODULE_0__.Unfolding),
/* harmony export */   "UnfoldingDetailPage": () => (/* reexport safe */ _unfolding_detail__WEBPACK_IMPORTED_MODULE_2__.UnfoldingDetailPage),
/* harmony export */   "UnfoldingPage": () => (/* reexport safe */ _unfolding__WEBPACK_IMPORTED_MODULE_3__.UnfoldingPage),
/* harmony export */   "UnfoldingService": () => (/* reexport safe */ _unfolding_service__WEBPACK_IMPORTED_MODULE_1__.UnfoldingService)
/* harmony export */ });
/* harmony import */ var _unfolding_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./unfolding.model */ 76466);
/* harmony import */ var _unfolding_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./unfolding.service */ 21550);
/* harmony import */ var _unfolding_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./unfolding-detail */ 97524);
/* harmony import */ var _unfolding__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./unfolding */ 57479);






/***/ }),

/***/ 97524:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding-detail.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "UnfoldingDetailPage": () => (/* binding */ UnfoldingDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _unfolding_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./unfolding-detail.html?ngResource */ 75554);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _unfolding_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./unfolding.service */ 21550);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let UnfoldingDetailPage = class UnfoldingDetailPage {
    constructor(navController, unfoldingService, activatedRoute, alertController) {
        this.navController = navController;
        this.unfoldingService = unfoldingService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.unfolding = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.unfolding = response.data;
            this.unfolding.hiveId = this.unfolding.hive.id;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/unfolding/' + item.id + '/edit');
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
                            this.unfoldingService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/unfolding');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
UnfoldingDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _unfolding_service__WEBPACK_IMPORTED_MODULE_1__.UnfoldingService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
UnfoldingDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-unfolding-detail',
        template: _unfolding_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], UnfoldingDetailPage);



/***/ }),

/***/ 8108:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding-update.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "UnfoldingUpdatePage": () => (/* binding */ UnfoldingUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _unfolding_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./unfolding-update.html?ngResource */ 69952);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _unfolding_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./unfolding.model */ 76466);
/* harmony import */ var _unfolding_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./unfolding.service */ 21550);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../hive */ 45734);









let UnfoldingUpdatePage = class UnfoldingUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, hiveService, unfoldingService, route, router) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.hiveService = hiveService;
        this.unfoldingService = unfoldingService;
        this.route = route;
        this.router = router;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            observations: [null, []],
            date: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            hiveId: [null, []],
            newHiveId: [null, []],
            newHiveCode: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
        this.route.queryParams.subscribe(params => {
            this.from = params.from;
            this.apiaryId = params.apiaryId;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ngOnInit() {
        this.hiveService.query({ 'apiaryId.equals': this.apiaryId }).subscribe(data => {
            this.hives = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.unfolding = response.data;
            this.isNew = this.unfolding.id === null || this.unfolding.id === undefined;
            this.updateForm(this.unfolding);
        });
    }
    updateForm(unfolding) {
        this.form.patchValue({
            id: unfolding.id,
            observations: unfolding.observations,
            date: this.isNew ? new Date().toISOString() : unfolding.date,
            hiveId: unfolding.hiveId,
        });
    }
    save() {
        this.isSaving = true;
        const unfolding = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.unfoldingService.update(unfolding));
        }
        else {
            this.subscribeToSaveResponse(this.unfoldingService.create(unfolding));
        }
    }
    subscribeToSaveResponseHive(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => {
            const hive = this.createFromFormHive();
            this.subscribeToSaveResponseHive(this.hiveService.create(hive));
        }, (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Unfolding ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/unfolding');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _unfolding_model__WEBPACK_IMPORTED_MODULE_1__.Unfolding()), { id: this.form.get(['id']).value, observations: this.form.get(['observations']).value, date: new Date(this.form.get(['date']).value), hiveId: this.form.get(['hiveId']).value });
    }
    createFromFormHive() {
        return Object.assign(Object.assign({}, new _hive__WEBPACK_IMPORTED_MODULE_3__.Hive()), { id: this.form.get(['newHiveId']).value, code: this.form.get(['newHiveCode']).value, apiaryId: parseInt(this.apiaryId) });
    }
    compareHive(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackHiveById(index, item) {
        return item.id;
    }
};
UnfoldingUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.ToastController },
    { type: _hive__WEBPACK_IMPORTED_MODULE_3__.HiveService },
    { type: _unfolding_service__WEBPACK_IMPORTED_MODULE_2__.UnfoldingService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.Router }
];
UnfoldingUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-unfolding-update',
        template: _unfolding_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], UnfoldingUpdatePage);



/***/ }),

/***/ 76466:
/*!*************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding.model.ts ***!
  \*************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Unfolding": () => (/* binding */ Unfolding)
/* harmony export */ });
class Unfolding {
    constructor(id, observations, date, hiveundefined, hiveId, hive) {
        this.id = id;
        this.observations = observations;
        this.date = date;
        this.hiveundefined = hiveundefined;
        this.hiveId = hiveId;
        this.hive = hive;
    }
}


/***/ }),

/***/ 35197:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding.module.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "UnfoldingPageModule": () => (/* binding */ UnfoldingPageModule),
/* harmony export */   "UnfoldingResolve": () => (/* binding */ UnfoldingResolve)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/auth/user-route-access.service */ 51284);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs */ 64139);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _unfolding__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./unfolding */ 57479);
/* harmony import */ var _unfolding_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./unfolding-update */ 8108);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 26386);













let UnfoldingResolve = class UnfoldingResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((unfolding) => unfolding.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Unfolding());
    }
};
UnfoldingResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.UnfoldingService }
];
UnfoldingResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], UnfoldingResolve);

const routes = [
    {
        path: '',
        component: _unfolding__WEBPACK_IMPORTED_MODULE_1__.UnfoldingPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _unfolding_update__WEBPACK_IMPORTED_MODULE_2__.UnfoldingUpdatePage,
        resolve: {
            data: UnfoldingResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.UnfoldingDetailPage,
        resolve: {
            data: UnfoldingResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _unfolding_update__WEBPACK_IMPORTED_MODULE_2__.UnfoldingUpdatePage,
        resolve: {
            data: UnfoldingResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let UnfoldingPageModule = class UnfoldingPageModule {
};
UnfoldingPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_unfolding__WEBPACK_IMPORTED_MODULE_1__.UnfoldingPage, _unfolding_update__WEBPACK_IMPORTED_MODULE_2__.UnfoldingUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.UnfoldingDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], UnfoldingPageModule);



/***/ }),

/***/ 21550:
/*!***************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding.service.ts ***!
  \***************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "UnfoldingService": () => (/* binding */ UnfoldingService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let UnfoldingService = class UnfoldingService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/unfoldings';
    }
    create(unfolding) {
        return this.http.post(this.resourceUrl, unfolding, { observe: 'response' });
    }
    update(unfolding) {
        return this.http.put(`${this.resourceUrl}/${unfolding.id}`, unfolding, { observe: 'response' });
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
UnfoldingService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
UnfoldingService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], UnfoldingService);



/***/ }),

/***/ 57479:
/*!*******************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding.ts ***!
  \*******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "UnfoldingPage": () => (/* binding */ UnfoldingPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _unfolding_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./unfolding.html?ngResource */ 87085);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _unfolding_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./unfolding.service */ 21550);






let UnfoldingPage = class UnfoldingPage {
    // todo: add pagination
    constructor(navController, unfoldingService, toastCtrl, plt) {
        this.navController = navController;
        this.unfoldingService = unfoldingService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.unfoldings = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.unfoldingService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.unfoldings = response;
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
            yield this.navController.navigateForward('/tabs/entities/unfolding/new');
        });
    }
    edit(item, unfolding) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/unfolding/' + unfolding.id + '/edit');
            yield item.close();
        });
    }
    delete(unfolding) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.unfoldingService.delete(unfolding.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Unfolding deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(unfolding) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/unfolding/' + unfolding.id + '/view');
        });
    }
};
UnfoldingPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _unfolding_service__WEBPACK_IMPORTED_MODULE_1__.UnfoldingService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
UnfoldingPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-unfolding',
        template: _unfolding_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], UnfoldingPage);



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

/***/ 75554:
/*!***************************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding-detail.html?ngResource ***!
  \***************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Desdobramento</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{unfolding.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Observações</ion-label>\r\n      <div item-content>\r\n        <span id=\"observations-content\">{{unfolding.observations}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Data</ion-label>\r\n      <div item-content>\r\n        <span id=\"date-content\">{{unfolding.date | date:'medium'}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Colmeia</ion-label>\r\n      <div item-content *ngIf=\"unfolding.hiveId\">\r\n        <a>{{unfolding.hiveId}}</a>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(unfolding)\">Editar</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(unfolding)\">Eliminar</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 69952:
/*!***************************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding-update.html?ngResource ***!
  \***************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Desdobramento</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Observações</ion-label>\r\n        <ion-input type=\"text\" name=\"observations\" formControlName=\"observations\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Data</ion-label>\r\n        <ion-datetime displayFormat=\"MM/DD/YYYY HH:mm\" formControlName=\"date\" id=\"field_date\"></ion-datetime>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Colmeia</ion-label>\r\n        <ion-select id=\"field_hive\" formControlName=\"hiveId\" [compareWith]=\"compareHive\">\r\n          <ion-select-option [value]=\"null\"></ion-select-option>\r\n          <ion-select-option [value]=\"hiveOption.id\" *ngFor=\"let hiveOption of hives; trackBy: trackHiveById\"\r\n            >{{hiveOption.id}}</ion-select-option\r\n          >\r\n        </ion-select>\r\n      </ion-item>\r\n      <ion-list [hidden]=\"!this.isNew\">\r\n        <ion-item [hidden]=\"true\">\r\n          <ion-label>ID</ion-label>\r\n          <ion-input type=\"hidden\" id=\"newHiveId\" formControlName=\"newHiveId\" readonly></ion-input>\r\n        </ion-item>\r\n        <ion-item>\r\n          <ion-label position=\"floating\">Código Nova Colmeia</ion-label>\r\n          <ion-input type=\"text\" name=\"newHiveCode\" formControlName=\"newHiveCode\"></ion-input>\r\n        </ion-item>\r\n      </ion-list>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ }),

/***/ 87085:
/*!********************************************************************!*\
  !*** ./src/app/pages/entities/unfolding/unfolding.html?ngResource ***!
  \********************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Desdobramentos</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let unfolding of unfoldings; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(unfolding)\">\r\n        <ion-label text-wrap>\r\n          <p>{{unfolding.id}}</p>\r\n          <p>{{unfolding.observations}}</p>\r\n          <p>{{unfolding.date | date:'medium'}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, unfolding)\">Editar</ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(unfolding)\">Eliminar</ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!unfoldings?.length\">\r\n    <ion-label> Nenhum Desdobramento encontrado. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab *ngIf=\"1==0\" vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_unfolding_unfolding_module_ts.js.map