"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_crest_crest_module_ts"],{

/***/ 20100:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/crest/crest-detail.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "CrestDetailPage": () => (/* binding */ CrestDetailPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _crest_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./crest-detail.html?ngResource */ 82223);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _crest_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./crest.model */ 2064);
/* harmony import */ var _crest_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./crest.service */ 87600);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ 52816);







let CrestDetailPage = class CrestDetailPage {
    constructor(navController, crestService, activatedRoute, alertController) {
        this.navController = navController;
        this.crestService = crestService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.crest = {};
        this.isFinalized = false;
    }
    ngOnInit() {
        this.activatedRoute.data.subscribe(response => {
            if (response.data.hive) {
                this.hiveDisplayed = response.data.hive;
            }
            this.crest = response.data;
            if (this.crest.state) {
                this.crest.state = _crest_model__WEBPACK_IMPORTED_MODULE_1__.CrestState[this.crest.state];
            }
            this.isFinalized = this.crest.state === _crest_model__WEBPACK_IMPORTED_MODULE_1__.CrestState.FINALIZED;
        });
    }
    open(item) {
        this.navController.navigateForward('/tabs/entities/crest/' + item.id + '/edit');
    }
    deleteModal(item) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__awaiter)(this, void 0, void 0, function* () {
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
                            this.crestService.delete(item.id).subscribe(() => {
                                this.navController.navigateForward('/tabs/entities/crest');
                            });
                        },
                    },
                ],
            });
            yield alert.present();
        });
    }
};
CrestDetailPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_4__.NavController },
    { type: _crest_service__WEBPACK_IMPORTED_MODULE_2__.CrestService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_5__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_4__.AlertController }
];
CrestDetailPage = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_6__.Component)({
        selector: 'page-crest-detail',
        template: _crest_detail_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], CrestDetailPage);



/***/ }),

/***/ 67813:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/crest/crest-update.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "CrestUpdatePage": () => (/* binding */ CrestUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _crest_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./crest-update.html?ngResource */ 74832);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _crest_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./crest.model */ 2064);
/* harmony import */ var _crest_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./crest.service */ 87600);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../hive */ 45734);










let CrestUpdatePage = class CrestUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, hiveService, crestService, route, router) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.hiveService = hiveService;
        this.crestService = crestService;
        this.route = route;
        this.router = router;
        this.isSaving = false;
        this.isNew = true;
        this.isFinalized = false;
        this.form = this.formBuilder.group({
            id: [null, []],
            combFrameQuantity: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            waxWeight: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            timeWastedCentrifuge: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            initialDateDecantation: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            finalDateDecantation: [null, []],
            producedHoneyQuantity: [null, []],
            state: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            hiveId: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
        this.route.queryParams.subscribe(params => {
            this.apiaryId = params.apiaryId;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ngOnInit() {
        let req = {};
        if (this.apiaryId) {
            req = { 'apiaryId.equals': this.apiaryId };
        }
        this.hiveService.query(req).subscribe(data => {
            this.hives = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.crest = response.data;
            if (response.data.hive) {
                this.crest.hiveId = response.data.hive.id;
            }
            this.isNew = this.crest.id === null || this.crest.id === undefined;
            this.isFinalized = this.crest.state === _crest_model__WEBPACK_IMPORTED_MODULE_1__.CrestState.FINALIZED;
            this.form.get('state').valueChanges.subscribe((value) => {
                const finalDateDecantation = this.form.get('finalDateDecantation');
                const producedHoneyQuantity = this.form.get('producedHoneyQuantity');
                if (value === 'FINALIZED') {
                    finalDateDecantation.setValidators([_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]);
                    producedHoneyQuantity.setValidators([_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]);
                }
                else {
                    finalDateDecantation.clearValidators();
                    producedHoneyQuantity.clearValidators();
                }
                finalDateDecantation.updateValueAndValidity();
                producedHoneyQuantity.updateValueAndValidity();
            });
            this.updateForm(this.crest);
        });
    }
    updateForm(crest) {
        this.form.patchValue({
            id: crest.id,
            combFrameQuantity: crest.combFrameQuantity,
            waxWeight: crest.waxWeight,
            timeWastedCentrifuge: crest.timeWastedCentrifuge,
            initialDateDecantation: this.isNew ? new Date().toISOString() : crest.initialDateDecantation,
            finalDateDecantation: this.isNew ? new Date().toISOString() : crest.finalDateDecantation,
            producedHoneyQuantity: crest.producedHoneyQuantity,
            state: crest.state,
            hiveId: crest.hiveId,
        });
    }
    save() {
        this.isSaving = true;
        const crest = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.crestService.update(crest));
        }
        else {
            this.subscribeToSaveResponse(this.crestService.create(crest));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Crest ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/crest');
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
        return Object.assign(Object.assign({}, new _crest_model__WEBPACK_IMPORTED_MODULE_1__.Crest()), { id: this.form.get(['id']).value, combFrameQuantity: this.form.get(['combFrameQuantity']).value, waxWeight: this.form.get(['waxWeight']).value, timeWastedCentrifuge: this.form.get(['timeWastedCentrifuge']).value, initialDateDecantation: new Date(this.form.get(['initialDateDecantation']).value), finalDateDecantation: new Date(this.form.get(['finalDateDecantation']).value), producedHoneyQuantity: this.form.get(['producedHoneyQuantity']).value, state: this.form.get(['state']).value, hiveId: this.form.get(['hiveId']).value });
    }
    compareHive(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackHiveById(index, item) {
        return item.id;
    }
};
CrestUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.ToastController },
    { type: _hive__WEBPACK_IMPORTED_MODULE_3__.HiveService },
    { type: _crest_service__WEBPACK_IMPORTED_MODULE_2__.CrestService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.Router }
];
CrestUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-crest-update',
        template: _crest_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], CrestUpdatePage);



/***/ }),

/***/ 2064:
/*!*****************************************************!*\
  !*** ./src/app/pages/entities/crest/crest.model.ts ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Crest": () => (/* binding */ Crest),
/* harmony export */   "CrestState": () => (/* binding */ CrestState)
/* harmony export */ });
var CrestState;
(function (CrestState) {
    CrestState["DECANTATION"] = "Decanta\u00E7\u00E3o";
    CrestState["FINALIZED"] = "Finalizado";
})(CrestState || (CrestState = {}));
class Crest {
    constructor(id, combFrameQuantity, waxWeight, timeWastedCentrifuge, initialDateDecantation, finalDateDecantation, producedHoneyQuantity, state, hiveundefined, hiveId) {
        this.id = id;
        this.combFrameQuantity = combFrameQuantity;
        this.waxWeight = waxWeight;
        this.timeWastedCentrifuge = timeWastedCentrifuge;
        this.initialDateDecantation = initialDateDecantation;
        this.finalDateDecantation = finalDateDecantation;
        this.producedHoneyQuantity = producedHoneyQuantity;
        this.state = state;
        this.hiveundefined = hiveundefined;
        this.hiveId = hiveId;
    }
}


/***/ }),

/***/ 44180:
/*!******************************************************!*\
  !*** ./src/app/pages/entities/crest/crest.module.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "CrestPageModule": () => (/* binding */ CrestPageModule),
/* harmony export */   "CrestResolve": () => (/* binding */ CrestResolve)
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
/* harmony import */ var _crest__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./crest */ 84659);
/* harmony import */ var _crest_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./crest-update */ 67813);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 83068);













let CrestResolve = class CrestResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((crest) => crest.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Crest());
    }
};
CrestResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.CrestService }
];
CrestResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], CrestResolve);

const routes = [
    {
        path: '',
        component: _crest__WEBPACK_IMPORTED_MODULE_1__.CrestPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _crest_update__WEBPACK_IMPORTED_MODULE_2__.CrestUpdatePage,
        resolve: {
            data: CrestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.CrestDetailPage,
        resolve: {
            data: CrestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _crest_update__WEBPACK_IMPORTED_MODULE_2__.CrestUpdatePage,
        resolve: {
            data: CrestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let CrestPageModule = class CrestPageModule {
};
CrestPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_crest__WEBPACK_IMPORTED_MODULE_1__.CrestPage, _crest_update__WEBPACK_IMPORTED_MODULE_2__.CrestUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.CrestDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], CrestPageModule);



/***/ }),

/***/ 87600:
/*!*******************************************************!*\
  !*** ./src/app/pages/entities/crest/crest.service.ts ***!
  \*******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "CrestService": () => (/* binding */ CrestService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ 28784);
/* harmony import */ var _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/api/api.service */ 45146);
/* harmony import */ var _shared__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../shared */ 51679);





let CrestService = class CrestService {
    constructor(http) {
        this.http = http;
        this.resourceUrl = _services_api_api_service__WEBPACK_IMPORTED_MODULE_0__.ApiService.API_URL + '/crests';
    }
    create(crest) {
        return this.http.post(this.resourceUrl, crest, { observe: 'response' });
    }
    update(crest) {
        return this.http.put(`${this.resourceUrl}/${crest.id}`, crest, { observe: 'response' });
    }
    find(id) {
        return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    query(req) {
        const options = (0,_shared__WEBPACK_IMPORTED_MODULE_1__.createRequestOption)(req);
        return this.http.get(this.resourceUrl, { params: options, observe: 'response' });
    }
    queryByUser(req) {
        const options = (0,_shared__WEBPACK_IMPORTED_MODULE_1__.createRequestOption)(req);
        return this.http.get(this.resourceUrl + "/byUser", { params: options, observe: 'response' });
    }
    delete(id) {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
};
CrestService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__.HttpClient }
];
CrestService = (0,tslib__WEBPACK_IMPORTED_MODULE_3__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_4__.Injectable)({ providedIn: 'root' })
], CrestService);



/***/ }),

/***/ 84659:
/*!***********************************************!*\
  !*** ./src/app/pages/entities/crest/crest.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "CrestPage": () => (/* binding */ CrestPage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _crest_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./crest.html?ngResource */ 63173);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _crest_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./crest.model */ 2064);
/* harmony import */ var _crest_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./crest.service */ 87600);
/* harmony import */ var _services_offline_connectivity_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../services/offline/connectivity.service */ 36629);
/* harmony import */ var _services_offline_storage_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../../services/offline/storage.service */ 35694);











let CrestPage = class CrestPage {
    // todo: add pagination
    constructor(navController, crestService, toastCtrl, plt, route, router, connectivityService, storageService) {
        this.navController = navController;
        this.crestService = crestService;
        this.toastCtrl = toastCtrl;
        this.plt = plt;
        this.route = route;
        this.router = router;
        this.connectivityService = connectivityService;
        this.storageService = storageService;
        this.crests = [];
        console.log(this.connectivityService.isOnline());
        this.route.queryParams.subscribe(params => {
            this.apiaryId = params.apiaryId;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ionViewWillEnter() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            yield this.loadAll();
        });
    }
    loadAll(refresher) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            if (this.connectivityService.isOnline()) {
                this.crestService
                    .queryByUser()
                    .pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_6__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_7__.map)((res) => res.body))
                    .subscribe((response) => {
                    this.crests = response;
                    this.crests.forEach(function (el) {
                        if (el.state) {
                            el.state = _crest_model__WEBPACK_IMPORTED_MODULE_1__.CrestState[el.state];
                        }
                    });
                    if (typeof refresher !== 'undefined') {
                        setTimeout(() => {
                            refresher.target.complete();
                        }, 750);
                    }
                }, (error) => (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
                    console.error(error);
                    const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
                    yield toast.present();
                }));
            }
            else {
                const crests = this.storageService.getValue("crests");
                console.log(crests);
            }
        });
    }
    trackId(index, item) {
        return item.id;
    }
    new() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/crest/new');
        });
    }
    edit(item, crest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/crest/' + crest.id + '/edit');
            yield item.close();
        });
    }
    delete(crest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            this.crestService.delete(crest.id).subscribe(() => (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
                const toast = yield this.toastCtrl.create({ message: 'Crest deleted successfully.', duration: 3000, position: 'middle' });
                yield toast.present();
                yield this.loadAll();
            }), error => console.error(error));
        });
    }
    view(crest) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            yield this.navController.navigateForward('/tabs/entities/crest/' + crest.id + '/view');
        });
    }
};
CrestPage.ctorParameters = () => [
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.NavController },
    { type: _crest_service__WEBPACK_IMPORTED_MODULE_2__.CrestService },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.ToastController },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.Platform },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_9__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_9__.Router },
    { type: _services_offline_connectivity_service__WEBPACK_IMPORTED_MODULE_3__.ConnectivityService },
    { type: _services_offline_storage_service__WEBPACK_IMPORTED_MODULE_4__.StorageService }
];
CrestPage = (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_10__.Component)({
        selector: 'page-crest',
        template: _crest_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], CrestPage);



/***/ }),

/***/ 83068:
/*!***********************************************!*\
  !*** ./src/app/pages/entities/crest/index.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Crest": () => (/* reexport safe */ _crest_model__WEBPACK_IMPORTED_MODULE_0__.Crest),
/* harmony export */   "CrestDetailPage": () => (/* reexport safe */ _crest_detail__WEBPACK_IMPORTED_MODULE_2__.CrestDetailPage),
/* harmony export */   "CrestPage": () => (/* reexport safe */ _crest__WEBPACK_IMPORTED_MODULE_3__.CrestPage),
/* harmony export */   "CrestService": () => (/* reexport safe */ _crest_service__WEBPACK_IMPORTED_MODULE_1__.CrestService),
/* harmony export */   "CrestState": () => (/* reexport safe */ _crest_model__WEBPACK_IMPORTED_MODULE_0__.CrestState)
/* harmony export */ });
/* harmony import */ var _crest_model__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./crest.model */ 2064);
/* harmony import */ var _crest_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./crest.service */ 87600);
/* harmony import */ var _crest_detail__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./crest-detail */ 20100);
/* harmony import */ var _crest__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./crest */ 84659);






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

/***/ 36629:
/*!**********************************************************!*\
  !*** ./src/app/services/offline/connectivity.service.ts ***!
  \**********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ConnectivityService": () => (/* binding */ ConnectivityService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_native_network_ngx__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ionic-native/network/ngx */ 99118);



let ConnectivityService = class ConnectivityService {
    constructor(network) {
        this.network = network;
        // Subscribe to network status changes
        this.network.onDisconnect().subscribe(() => {
            console.log('Disconnected from the internet');
            // Handle disconnection if needed
        });
        this.network.onConnect().subscribe(() => {
            console.log('Connected to the internet');
            // Handle connection if needed
        });
    }
    // Function to check if the device is online
    isOnline() {
        return this.network.type !== 'none';
    }
};
ConnectivityService.ctorParameters = () => [
    { type: _ionic_native_network_ngx__WEBPACK_IMPORTED_MODULE_0__.Network }
];
ConnectivityService = (0,tslib__WEBPACK_IMPORTED_MODULE_1__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_2__.Injectable)({
        providedIn: 'root'
    })
], ConnectivityService);



/***/ }),

/***/ 35694:
/*!*****************************************************!*\
  !*** ./src/app/services/offline/storage.service.ts ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "StorageService": () => (/* binding */ StorageService)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ionic_storage_angular__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ionic/storage-angular */ 80190);



let StorageService = class StorageService {
    constructor(storage) {
        this.storage = storage;
        this.createStorage();
    }
    createStorage() {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__awaiter)(this, void 0, void 0, function* () {
            yield this.storage.create();
        });
    }
    // Functions to interact with storage
    setValue(key, value) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__awaiter)(this, void 0, void 0, function* () {
            yield this.storage.set(key, value);
        });
    }
    getValue(key) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__awaiter)(this, void 0, void 0, function* () {
            return yield this.storage.get(key);
        });
    }
};
StorageService.ctorParameters = () => [
    { type: _ionic_storage_angular__WEBPACK_IMPORTED_MODULE_1__.Storage }
];
StorageService = (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_2__.Injectable)({
        providedIn: 'root'
    })
], StorageService);



/***/ }),

/***/ 82223:
/*!*******************************************************************!*\
  !*** ./src/app/pages/entities/crest/crest-detail.html?ngResource ***!
  \*******************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Crest</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <ion-list>\n    <ion-item>\n      <ion-label position=\"fixed\">ID</ion-label>\n      <div item-content>\n        <span id=\"id-content\">{{crest.id}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Quantidade de Quadros (Un.)</ion-label>\n      <div item-content>\n        <span id=\"combFrameQuantity-content\">{{crest.combFrameQuantity}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Peso de cera (KG)</ion-label>\n      <div item-content>\n        <span id=\"waxWeight-content\">{{crest.waxWeight}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Tenmpo gasto na Centrifugadora (Min.)</ion-label>\n      <div item-content>\n        <span id=\"timeWastedCentrifuge-content\">{{crest.timeWastedCentrifuge}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Início da Decantação</ion-label>\n      <div item-content>\n        <span id=\"initialDateDecantation-content\">{{crest.initialDateDecantation | date:'medium'}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Final da Decantação</ion-label>\n      <div item-content>\n        <span id=\"finalDateDecantation-content\">{{crest.finalDateDecantation | date:'medium'}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Quantidade de Mel (KG)</ion-label>\n      <div item-content>\n        <span id=\"producedHoneyQuantity-content\">{{crest.producedHoneyQuantity}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label position=\"fixed\">Estado</ion-label>\n      <div item-content>\n        <span id=\"state-content\">{{crest.state}}</span>\n      </div>\n    </ion-item>\n    <ion-item>\n      <ion-label>Colmeia</ion-label>\n      <div item-content *ngIf=\"hiveDisplayed && hiveDisplayed.id\">\n        <a>{{hiveDisplayed.id}}</a>\n      </div>\n    </ion-item>\n  </ion-list>\n\n  <ion-button *ngIf=\"!isFinalized\" expand=\"block\" color=\"primary\" (click)=\"open(crest)\">{{ 'EDIT_BUTTON' | translate }}</ion-button>\n  <ion-button expand=\"block\" color=\"danger\" (click)=\"deleteModal(crest)\">{{ 'DELETE_BUTTON' | translate }}</ion-button>\n</ion-content>\n";

/***/ }),

/***/ 74832:
/*!*******************************************************************!*\
  !*** ./src/app/pages/entities/crest/crest-update.html?ngResource ***!
  \*******************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Cresta</ion-title>\n\n    <ion-buttons slot=\"end\">\n      <ion-button *ngIf=\"!this.isFinalized\" [disabled]=\"!isReadyToSave\"  (click)=\"save()\" color=\"primary\">\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\n      </ion-button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\n    <ion-list>\n      <ion-item [hidden]=\"!form.id\">\n        <ion-label>ID</ion-label>\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label>Colmeia</ion-label>\n        <ion-select id=\"field_hive\" formControlName=\"hiveId\" [value]=\"this.crest.hiveId\" [compareWith]=\"compareHive\" [disabled]=\"!isNew\">\n          <ion-select-option [value]=\"hiveOption.id\" *ngFor=\"let hiveOption of hives; trackBy: trackHiveById\"\n            >{{hiveOption.id}}</ion-select-option\n          >\n        </ion-select>\n      </ion-item>\n\n      <ion-item>\n        <ion-label position=\"floating\">Quantidade de Quadros (Un.)</ion-label>\n        <ion-input [disabled]=\"!isNew\" type=\"number\" name=\"combFrameQuantity\" formControlName=\"combFrameQuantity\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label>Estado</ion-label>\n        <ion-select formControlName=\"state\" id=\"field_state\" required >\n          <ion-select-option value=\"DECANTATION\">Decantação</ion-select-option>\n          <ion-select-option value=\"FINALIZED\">Finalizado</ion-select-option>\n        </ion-select>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Peso de Cera (KG)</ion-label>\n        <ion-input type=\"number\" name=\"waxWeight\" formControlName=\"waxWeight\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Tempo gasto na Centrifugadora (Minutes)</ion-label>\n        <ion-input type=\"number\" name=\"timeWastedCentrifuge\" formControlName=\"timeWastedCentrifuge\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label >Início da Decantação</ion-label>\n        <ion-datetime\n          [disabled]=\"!isNew\"\n          displayFormat=\"MM/DD/YYYY HH:mm\"\n          formControlName=\"initialDateDecantation\"\n          id=\"field_initialDateDecantation\"\n          presentation=\"date-time\"\n        ></ion-datetime>\n      </ion-item>\n      <ion-item>\n        <ion-label>Final da Decantação</ion-label>\n        <ion-datetime\n          displayFormat=\"MM/DD/YYYY HH:mm\"\n          formControlName=\"finalDateDecantation\"\n          id=\"field_finalDateDecantation\"\n          presentation=\"date-time\"\n        ></ion-datetime>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Quantidade de Mel (KG)</ion-label>\n        <ion-input type=\"number\" name=\"producedHoneyQuantity\" formControlName=\"producedHoneyQuantity\"></ion-input>\n      </ion-item>\n\n\n    </ion-list>\n  </form>\n</ion-content>\n";

/***/ }),

/***/ 63173:
/*!************************************************************!*\
  !*** ./src/app/pages/entities/crest/crest.html?ngResource ***!
  \************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Crestas</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<!-- todo: add elasticsearch support -->\n<ion-content class=\"ion-padding\">\n  <ion-refresher [disabled]=\"plt.is('desktop')\" slot=\"fixed\" (ionRefresh)=\"loadAll($event)\">\n    <ion-refresher-content></ion-refresher-content>\n  </ion-refresher>\n\n  <ion-list>\n    <ion-item-sliding *ngFor=\"let crest of crests; trackBy: trackId\" #slidingItem>\n      <ion-item (click)=\"view(crest)\">\n        <ion-label text-wrap>\n          <p>ID: {{crest.id}}</p>\n          <p>{{crest.combFrameQuantity}}</p>\n          <p>{{crest.waxWeight}}</p>\n          <p>{{crest.timeWastedCentrifuge}}</p>\n          <p>{{crest.initialDateDecantation | date:'medium'}}</p>\n          <p>{{crest.finalDateDecantation | date:'medium'}}</p>\n          <p>{{crest.producedHoneyQuantity}}</p>\n          <!-- todo: special handling for translating enum - {{'CrestState.' + crest.state}}\" -->\n          <p>{{crest.state}}</p>\n        </ion-label>\n      </ion-item>\n      <ion-item-options side=\"end\">\n        <ion-item-option color=\"primary\" (click)=\"edit(slidingItem, crest)\"> {{ 'EDIT_BUTTON' | translate }} </ion-item-option>\n        <ion-item-option color=\"danger\" (click)=\"delete(crest)\"> {{ 'DELETE_BUTTON' | translate }} </ion-item-option>\n      </ion-item-options>\n    </ion-item-sliding>\n  </ion-list>\n  <ion-item *ngIf=\"!crests?.length\">\n    <ion-label> Sem crestas encontradas. </ion-label>\n  </ion-item>\n\n  <ion-fab *ngIf=\"1==0\" vertical=\"bottom\" horizontal=\"end\" slot=\"fixed\">\n    <ion-fab-button (click)=\"new()\">\n      <ion-icon name=\"add\"></ion-icon>\n    </ion-fab-button>\n  </ion-fab>\n</ion-content>\n";

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
//# sourceMappingURL=src_app_pages_entities_crest_crest_module_ts.js.map