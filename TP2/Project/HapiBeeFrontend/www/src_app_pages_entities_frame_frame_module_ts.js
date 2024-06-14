"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_frame_frame_module_ts"],{

/***/ 40693:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/frame/frame-detail.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FrameDetailPage": () => (/* binding */ FrameDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _frame_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./frame-detail.html?ngResource */ 74218);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _frame_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./frame.service */ 25029);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ 52816);






let FrameDetailPage = class FrameDetailPage {
    constructor(navController, frameService, activatedRoute, alertController) {
        this.navController = navController;
        this.frameService = frameService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.frame = {};
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            this.frame = response.data;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/frame/' + item.id + '/edit');
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
                            this.frameService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/frame');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
FrameDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.NavController },
    { type: _frame_service__WEBPACK_IMPORTED_MODULE_1__.FrameService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_3__.AlertController }
];
FrameDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_5__.Component)({
        selector: 'page-frame-detail',
        template: _frame_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], FrameDetailPage);



/***/ }),

/***/ 4095:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/frame/frame-update.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FrameUpdatePage": () => (/* binding */ FrameUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _frame_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./frame-update.html?ngResource */ 92260);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _frame_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./frame.model */ 39963);
/* harmony import */ var _frame_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./frame.service */ 25029);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../hive */ 45734);









let FrameUpdatePage = class FrameUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, hiveService, frameService) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.hiveService = hiveService;
        this.frameService = frameService;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            code: [null, []],
            hiveId: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        this.hiveService.query().subscribe(data => {
            this.hives = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.frame = response.data;
            this.isNew = this.frame.id === null || this.frame.id === undefined;
            this.updateForm(this.frame);
        });
    }
    updateForm(frame) {
        this.form.patchValue({
            id: frame.id,
            code: frame.code,
            hiveId: frame.hiveId,
        });
    }
    save() {
        this.isSaving = true;
        const frame = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.frameService.update(frame));
        }
        else {
            this.subscribeToSaveResponse(this.frameService.create(frame));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_4__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Frame ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/frame');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_4__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _frame_model__WEBPACK_IMPORTED_MODULE_1__.Frame()), { id: this.form.get(['id']).value, code: this.form.get(['code']).value, hiveId: this.form.get(['hiveId']).value });
    }
    compareHive(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackHiveById(index, item) {
        return item.id;
    }
};
FrameUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_5__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_7__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_6__.ToastController },
    { type: _hive__WEBPACK_IMPORTED_MODULE_3__.HiveService },
    { type: _frame_service__WEBPACK_IMPORTED_MODULE_2__.FrameService }
];
FrameUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_4__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-frame-update',
        template: _frame_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], FrameUpdatePage);



/***/ }),

/***/ 39963:
/*!*****************************************************!*\
  !*** ./src/app/pages/entities/frame/frame.model.ts ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Frame": () => (/* binding */ Frame)
/* harmony export */ });
class Frame {
    constructor(id, code, hiveundefined, hiveId) {
        this.id = id;
        this.code = code;
        this.hiveundefined = hiveundefined;
        this.hiveId = hiveId;
    }
}


/***/ }),

/***/ 21354:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/frame/frame.module.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FramePageModule": () => (/* binding */ FramePageModule),
/* harmony export */   "FrameResolve": () => (/* binding */ FrameResolve)
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
/* harmony import */ var _frame__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./frame */ 91106);
/* harmony import */ var _frame_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./frame-update */ 4095);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 31236);













let FrameResolve = class FrameResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((frame) => frame.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Frame());
    }
};
FrameResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.FrameService }
];
FrameResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], FrameResolve);

const routes = [
    {
        path: '',
        component: _frame__WEBPACK_IMPORTED_MODULE_1__.FramePage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _frame_update__WEBPACK_IMPORTED_MODULE_2__.FrameUpdatePage,
        resolve: {
            data: FrameResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.FrameDetailPage,
        resolve: {
            data: FrameResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _frame_update__WEBPACK_IMPORTED_MODULE_2__.FrameUpdatePage,
        resolve: {
            data: FrameResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let FramePageModule = class FramePageModule {
};
FramePageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_frame__WEBPACK_IMPORTED_MODULE_1__.FramePage, _frame_update__WEBPACK_IMPORTED_MODULE_2__.FrameUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.FrameDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], FramePageModule);



/***/ }),

/***/ 25029:
/*!*******************************************************!*\
  !*** ./src/app/pages/entities/frame/frame.service.ts ***!
  \*******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FrameService": () => (/* binding */ FrameService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let FrameService = class FrameService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/frames';
    }
    create(frame) {
        return this.http.post(this.resourceUrl, frame, { observe: 'response' });
    }
    update(frame) {
        return this.http.put(`${this.resourceUrl}/${frame.id}`, frame, { observe: 'response' });
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
FrameService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
FrameService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], FrameService);



/***/ }),

/***/ 91106:
/*!***********************************************!*\
  !*** ./src/app/pages/entities/frame/frame.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FramePage": () => (/* binding */ FramePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _frame_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./frame.html?ngResource */ 49257);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _frame_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./frame.service */ 25029);






let FramePage = class FramePage {
    // todo: add pagination
    constructor(navController, frameService, toastCtrl, plt) {
        this.navController = navController;
        this.frameService = frameService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.frames = [];
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.frameService
                .query()
                .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_3__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.map)((res) => res.body))
                .subscribe((response) => {
                this.frames = response;
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
            yield this.navController.navigateForward('/tabs/entities/frame/new');
        });
    }
    edit(item, frame) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/frame/' + frame.id + '/edit');
            yield item.close();
        });
    }
    delete(frame) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            this.frameService.delete(frame.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Frame deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(frame) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/frame/' + frame.id + '/view');
        });
    }
};
FramePage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.NavController },
    { type: _frame_service__WEBPACK_IMPORTED_MODULE_1__.FrameService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_5__.Platform }
];
FramePage = (0,tslib__WEBPACK_IMPORTED_MODULE_2__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-frame',
        template: _frame_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], FramePage);



/***/ }),

/***/ 31236:
/*!***********************************************!*\
  !*** ./src/app/pages/entities/frame/index.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Frame": () => (/* reexport safe */ _frame_model__WEBPACK_IMPORTED_MODULE_0__.Frame),
/* harmony export */   "FrameDetailPage": () => (/* reexport safe */ _frame_detail__WEBPACK_IMPORTED_MODULE_2__.FrameDetailPage),
/* harmony export */   "FramePage": () => (/* reexport safe */ _frame__WEBPACK_IMPORTED_MODULE_3__.FramePage),
/* harmony export */   "FrameService": () => (/* reexport safe */ _frame_service__WEBPACK_IMPORTED_MODULE_1__.FrameService)
/* harmony export */ });
/* harmony import */ var _frame_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./frame.model */ 39963);
/* harmony import */ var _frame_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./frame.service */ 25029);
/* harmony import */ var _frame_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./frame-detail */ 40693);
/* harmony import */ var _frame__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./frame */ 91106);






/***/ }),

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

/***/ 74218:
/*!*******************************************************************!*\
  !*** ./src/app/pages/entities/frame/frame-detail.html?ngResource ***!
  \*******************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Frame</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <ion-list>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">ID</ion-label>\r\n      <div item-content>\r\n        <span id=\"id-content\">{{frame.id}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label position=\"fixed\">Code</ion-label>\r\n      <div item-content>\r\n        <span id=\"code-content\">{{frame.code}}</span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-item>\r\n      <ion-label>Hive</ion-label>\r\n      <div item-content *ngIf=\"frame.hiveId\">\r\n        <a>{{frame.hiveundefined}}</a>\r\n      </div>\r\n    </ion-item>\r\n  </ion-list>\r\n\r\n  <ion-button expand=\"block\" color=\"primary\" (click)=\"open(frame)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\r\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(frame)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\r\n</ion-content>\r\n";

/***/ }),

/***/ 92260:
/*!*******************************************************************!*\
  !*** ./src/app/pages/entities/frame/frame-update.html?ngResource ***!
  \*******************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Frame</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Code</ion-label>\r\n        <ion-input type=\"text\" name=\"code\" formControlName=\"code\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Hive</ion-label>\r\n        <ion-select id=\"field_hive\" formControlName=\"hiveId\" [compareWith]=\"compareHive\">\r\n          <ion-select-option [value]=\"null\"></ion-select-option>\r\n          <ion-select-option [value]=\"hiveOption.id\" *ngFor=\"let hiveOption of hives; trackBy: trackHiveById\"\r\n            >{{hiveOption.id}}</ion-select-option\r\n          >\r\n        </ion-select>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ }),

/***/ 49257:
/*!************************************************************!*\
  !*** ./src/app/pages/entities/frame/frame.html?ngResource ***!
  \************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Frames</ion-title>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<!-- todo: add elasticsearch support -->\r\n<ion-content class=\"ion-padding\">\r\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\r\n    <ion-refresher-content></ion-refresher-content>\r\n  </ion-refresher>\r\n\r\n  <ion-list>\r\n    <ion-item-sliding *ngFor=\"let frame of frames; trackBy: trackId\" #slidingItem>\r\n      <ion-item (click)=\"view(frame)\">\r\n        <ion-label text-wrap>\r\n          <p>{{frame.id}}</p>\r\n          <p>{{frame.code}}</p>\r\n        </ion-label>\r\n      </ion-item>\r\n      <ion-item-options side=\"end\">\r\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, frame)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\r\n        <ion-item-option color=\"danger\" (click)=\"delete(frame)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\r\n      </ion-item-options>\r\n    </ion-item-sliding>\r\n  </ion-list>\r\n  <ion-item *ngIf=\"!frames?.length\">\r\n    <ion-label> No Frames found. </ion-label>\r\n  </ion-item>\r\n\r\n  <ion-fab vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\r\n    <ion-fab-button (click)=\"new()\">\r\n      <ion-icon name=\"add\"></ion-icon>\r\n    </ion-fab-button>\r\n  </ion-fab>\r\n</ion-content>\r\n";

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

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_frame_frame_module_ts.js.map