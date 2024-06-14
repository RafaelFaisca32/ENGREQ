"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_transhumance-request_transhumance-request_module_ts"],{

/***/ 66263:
/*!************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request-update.ts ***!
  \************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequestUpdatePage": () => (/* binding */ TranshumanceRequestUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _transhumance_request_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./transhumance-request-update.html?ngResource */ 61513);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _transhumance_request_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./transhumance-request.model */ 48559);
/* harmony import */ var _transhumance_request_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./transhumance-request.service */ 91296);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../apiary */ 50180);
/* harmony import */ var _hive__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../hive */ 45734);










let TranshumanceRequestUpdatePage = class TranshumanceRequestUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, apiaryService, hiveService, transhumanceRequestService) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.apiaryService = apiaryService;
        this.hiveService = hiveService;
        this.transhumanceRequestService = transhumanceRequestService;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            apiaryId: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            destinationApiaryId: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            hives: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        this.apiaryService.query({ 'beekeeperId.equals': localStorage.getItem("beekeeperId") }).subscribe(data => {
            this.apiaries = data.body;
        }, error => this.onError(error));
        this.loadHivesForApiary();
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
        this.activatedRoute.data.subscribe(response => {
            this.transhumanceRequest = response.data;
            this.isNew = this.transhumanceRequest.id === null || this.transhumanceRequest.id === undefined;
            this.updateForm(this.transhumanceRequest);
        });
    }
    loadHivesForApiary() {
        const apiaryId = this.form.get('apiaryId').value;
        if (apiaryId) {
            this.hiveService.query({ 'apiaryId.equals': apiaryId }).subscribe(data => {
                this.hives = data.body;
            }, error => this.onError(error));
        }
        else {
            this.hives = [];
        }
    }
    updateForm(transhumanceRequest) {
        this.form.patchValue({
            id: transhumanceRequest.id,
            requestDate: this.isNew ? new Date().toISOString() : transhumanceRequest.requestDate,
            state: transhumanceRequest.state,
            destinationApiaryId: transhumanceRequest.destinationApiaryId,
            apiaryId: transhumanceRequest.apiaryId,
            hives: transhumanceRequest.hives,
        });
        this.loadHivesForApiary();
    }
    onApiaryChange(event) {
        this.loadHivesForApiary();
    }
    save() {
        this.isSaving = true;
        const transhumanceRequest = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.transhumanceRequestService.update(transhumanceRequest));
        }
        else {
            this.subscribeToSaveResponse(this.transhumanceRequestService.create(transhumanceRequest));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({
                message: `TranshumanceRequest ${action} successfully.`,
                duration: 2000,
                position: 'middle',
            });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/transhumance-request');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _transhumance_request_model__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequest()), { id: this.form.get(['id']).value, apiaryId: this.form.get(['destinationApiaryId']).value, hives: this.form.get(['hives']).value });
    }
    compareApiary(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackApiaryById(index, item) {
        return item.id;
    }
    compareHive(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackHiveById(index, item) {
        return item.id;
    }
};
TranshumanceRequestUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_7__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_8__.ToastController },
    { type: _apiary__WEBPACK_IMPORTED_MODULE_3__.ApiaryService },
    { type: _hive__WEBPACK_IMPORTED_MODULE_4__.HiveService },
    { type: _transhumance_request_service__WEBPACK_IMPORTED_MODULE_2__.TranshumanceRequestService }
];
TranshumanceRequestUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_6__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_9__.Component)({
        selector: 'page-transhumance-request-update',
        template: _transhumance_request_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], TranshumanceRequestUpdatePage);



/***/ }),

/***/ 93956:
/*!************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request.module.ts ***!
  \************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "TranshumanceRequestPageModule": () => (/* binding */ TranshumanceRequestPageModule),
/* harmony export */   "TranshumanceRequestResolve": () => (/* binding */ TranshumanceRequestResolve)
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
/* harmony import */ var _transhumance_request__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./transhumance-request */ 64911);
/* harmony import */ var _transhumance_request_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./transhumance-request-update */ 66263);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 79524);













let TranshumanceRequestResolve = class TranshumanceRequestResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((transhumanceRequest) => transhumanceRequest.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.TranshumanceRequest());
    }
};
TranshumanceRequestResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.TranshumanceRequestService }
];
TranshumanceRequestResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], TranshumanceRequestResolve);

const routes = [
    {
        path: '',
        component: _transhumance_request__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequestPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _transhumance_request_update__WEBPACK_IMPORTED_MODULE_2__.TranshumanceRequestUpdatePage,
        resolve: {
            data: TranshumanceRequestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.TranshumanceRequestDetailPage,
        resolve: {
            data: TranshumanceRequestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _transhumance_request_update__WEBPACK_IMPORTED_MODULE_2__.TranshumanceRequestUpdatePage,
        resolve: {
            data: TranshumanceRequestResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let TranshumanceRequestPageModule = class TranshumanceRequestPageModule {
};
TranshumanceRequestPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_transhumance_request__WEBPACK_IMPORTED_MODULE_1__.TranshumanceRequestPage, _transhumance_request_update__WEBPACK_IMPORTED_MODULE_2__.TranshumanceRequestUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.TranshumanceRequestDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], TranshumanceRequestPageModule);



/***/ }),

/***/ 61513:
/*!*************************************************************************************************!*\
  !*** ./src/app/pages/entities/transhumance-request/transhumance-request-update.html?ngResource ***!
  \*************************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Pedido de transumância</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Apiário</ion-label>\r\n        <ion-select id=\"field_apiary\" formControlName=\"apiaryId\" [compareWith]=\"compareApiary\" (ionChange)=\"onApiaryChange($event)\" required>\r\n          <ion-select-option [value]=\"null\"></ion-select-option>\r\n          <ion-select-option [value]=\"apiaryOption.id\" *ngFor=\"let apiaryOption of apiaries; trackBy: trackApiaryById\"\r\n            >{{apiaryOption.id}}</ion-select-option\r\n          >\r\n        </ion-select>\r\n      </ion-item>\r\n      <ion-item *ngIf=\"form.get('apiaryId').value\">\r\n        <ion-label>Colmeias</ion-label>\r\n        <ng-container *ngIf=\"hives && hives.length > 0; else noHivesMessage\">\r\n          <ion-select id=\"field_hive\" multiple=\"true\" formControlName=\"hives\" [compareWith]=\"compareHive\" required>\r\n            <ion-select-option [value]=\"hiveOption\" *ngFor=\"let hiveOption of hives; trackBy: trackHiveById\">\r\n              {{hiveOption.id}}\r\n            </ion-select-option>\r\n          </ion-select>\r\n        </ng-container>\r\n        <ng-template #noHivesMessage>\r\n          <ion-text color=\"danger\">Este apiário ainda não tem colmeias.</ion-text>\r\n        </ng-template>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Apiário destino</ion-label>\r\n        <ion-select id=\"field_destinationApiary\" formControlName=\"destinationApiaryId\" [compareWith]=\"compareApiary\">\r\n          <ion-select-option [value]=\"null\"></ion-select-option>\r\n          <ion-select-option [value]=\"apiaryOption.id\" *ngFor=\"let apiaryOption of apiaries; trackBy: trackApiaryById\">\r\n            {{apiaryOption.id}}\r\n          </ion-select-option>\r\n        </ion-select>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_transhumance-request_transhumance-request_module_ts.js.map