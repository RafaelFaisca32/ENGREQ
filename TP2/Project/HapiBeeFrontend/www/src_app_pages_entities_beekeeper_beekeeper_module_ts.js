"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_beekeeper_beekeeper_module_ts"],{

/***/ 2306:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/beekeeper/beekeeper-update.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "BeekeeperUpdatePage": () => (/* binding */ BeekeeperUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _beekeeper_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./beekeeper-update.html?ngResource */ 92710);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _beekeeper_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./beekeeper.model */ 63893);
/* harmony import */ var _beekeeper_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./beekeeper.service */ 7932);
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../services/user/user.service */ 9709);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs/operators */ 86942);










let BeekeeperUpdatePage = class BeekeeperUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, userService, beekeeperService, route, router) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.userService = userService;
        this.beekeeperService = beekeeperService;
        this.route = route;
        this.router = router;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            beekeeperCompleteName: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            beekeeperNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            entityZoneResidence: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            nif: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            address: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            postalCode: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            phoneNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            faxNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            userId: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
        this.route.queryParams.subscribe(params => {
            this.from = params.from;
            if (this.router.getCurrentNavigation().extras.state) {
                //this.from = this.router.getCurrentNavigation().extras.state.from;
                //console.log(this.from)
            }
        });
    }
    ngOnInit() {
        this.userService.findAll().subscribe(data => {
            this.users = [];
            for (let i = 0; i < data.length; i++) {
                this.beekeeperService.query({ 'userId.equals': data[i].id }).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_6__.map)((res) => res.body))
                    .subscribe((response) => {
                    if (response.length == 0) {
                        this.users.push(data[i]);
                    }
                });
            }
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.beekeeper = response.data;
            this.isNew = this.beekeeper.id === null || this.beekeeper.id === undefined;
            this.updateForm(this.beekeeper);
        });
    }
    updateForm(beekeeper) {
        this.form.patchValue({
            id: beekeeper.id,
            beekeeperCompleteName: beekeeper.beekeeperCompleteName,
            beekeeperNumber: beekeeper.beekeeperNumber,
            entityZoneResidence: beekeeper.entityZoneResidence,
            nif: beekeeper.nif,
            address: beekeeper.address,
            postalCode: beekeeper.postalCode,
            phoneNumber: beekeeper.phoneNumber,
            faxNumber: beekeeper.faxNumber,
            userId: beekeeper.userId,
        });
    }
    save() {
        this.isSaving = true;
        const beekeeper = this.createFromForm();
        if (this.from == 'newUser' && localStorage.getItem('userId') != "") {
            beekeeper.userId = parseInt(localStorage.getItem('userId'));
        }
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.beekeeperService.update(beekeeper));
        }
        else {
            this.subscribeToSaveResponse(this.beekeeperService.create(beekeeper));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => {
            localStorage.setItem('beekeeperId', res.body.id.toString());
            return this.onSaveSuccess(res);
        }, (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Beekeeper ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/home');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _beekeeper_model__WEBPACK_IMPORTED_MODULE_1__.Beekeeper()), { id: this.form.get(['id']).value, beekeeperCompleteName: this.form.get(['beekeeperCompleteName']).value, beekeeperNumber: this.form.get(['beekeeperNumber']).value, entityZoneResidence: this.form.get(['entityZoneResidence']).value, nif: this.form.get(['nif']).value, address: this.form.get(['address']).value, postalCode: this.form.get(['postalCode']).value, phoneNumber: this.form.get(['phoneNumber']).value, faxNumber: this.form.get(['faxNumber']).value, userId: this.form.get(['userId']).value });
    }
    compareUser(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackUserById(index, item) {
        return item.id;
    }
};
BeekeeperUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_8__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.ToastController },
    { type: _services_user_user_service__WEBPACK_IMPORTED_MODULE_3__.UserService },
    { type: _beekeeper_service__WEBPACK_IMPORTED_MODULE_2__.BeekeeperService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_8__.ActivatedRoute },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_8__.Router }
];
BeekeeperUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_10__.Component)({
        selector: 'page-beekeeper-update',
        template: _beekeeper_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], BeekeeperUpdatePage);



/***/ }),

/***/ 87382:
/*!**************************************************************!*\
  !*** ./src/app/pages/entities/beekeeper/beekeeper.module.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "BeekeeperPageModule": () => (/* binding */ BeekeeperPageModule),
/* harmony export */   "BeekeeperResolve": () => (/* binding */ BeekeeperResolve)
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
/* harmony import */ var _beekeeper__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./beekeeper */ 54644);
/* harmony import */ var _beekeeper_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./beekeeper-update */ 2306);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 1439);













let BeekeeperResolve = class BeekeeperResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((beekeeper) => beekeeper.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Beekeeper());
    }
};
BeekeeperResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.BeekeeperService }
];
BeekeeperResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], BeekeeperResolve);

const routes = [
    {
        path: '',
        component: _beekeeper__WEBPACK_IMPORTED_MODULE_1__.BeekeeperPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _beekeeper_update__WEBPACK_IMPORTED_MODULE_2__.BeekeeperUpdatePage,
        resolve: {
            data: BeekeeperResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.BeekeeperDetailPage,
        resolve: {
            data: BeekeeperResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _beekeeper_update__WEBPACK_IMPORTED_MODULE_2__.BeekeeperUpdatePage,
        resolve: {
            data: BeekeeperResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let BeekeeperPageModule = class BeekeeperPageModule {
};
BeekeeperPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_beekeeper__WEBPACK_IMPORTED_MODULE_1__.BeekeeperPage, _beekeeper_update__WEBPACK_IMPORTED_MODULE_2__.BeekeeperUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.BeekeeperDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], BeekeeperPageModule);



/***/ }),

/***/ 92710:
/*!***************************************************************************!*\
  !*** ./src/app/pages/entities/beekeeper/beekeeper-update.html?ngResource ***!
  \***************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Apicultor</ion-title>\n\n    <ion-buttons slot=\"end\">\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\n      </ion-button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\n    <ion-list>\n      <ion-item [hidden]=\"!form.id\">\n        <ion-label>ID</ion-label>\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Nome Completo</ion-label>\n        <ion-input type=\"text\" name=\"beekeeperCompleteName\" formControlName=\"beekeeperCompleteName\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Número do Apicultor</ion-label>\n        <ion-input type=\"number\" name=\"beekeeperNumber\" formControlName=\"beekeeperNumber\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Zona de Residência</ion-label>\n        <ion-input type=\"number\" name=\"entityZoneResidence\" formControlName=\"entityZoneResidence\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">NIF</ion-label>\n        <ion-input type=\"number\" name=\"nif\" formControlName=\"nif\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Morada</ion-label>\n        <ion-input type=\"text\" name=\"address\" formControlName=\"address\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Código Postal</ion-label>\n        <ion-input type=\"text\" name=\"postalCode\" formControlName=\"postalCode\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Número de Telemóvel</ion-label>\n        <ion-input type=\"number\" name=\"phoneNumber\" formControlName=\"phoneNumber\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Fax</ion-label>\n        <ion-input type=\"number\" name=\"faxNumber\" formControlName=\"faxNumber\"></ion-input>\n      </ion-item>\n      <ion-item [hidden]=\"from === 'newUser'\">\n        <ion-label>User</ion-label>\n        <ion-select id=\"field_user\" formControlName=\"userId\" [compareWith]=\"compareUser\">\n          <ion-select-option [value]=\"null\"></ion-select-option>\n          <ion-select-option [value]=\"userOption.id\" *ngFor=\"let userOption of users; trackBy: trackUserById\"\n            >{{userOption.id}}</ion-select-option\n          >\n        </ion-select>\n      </ion-item>\n    </ion-list>\n  </form>\n</ion-content>\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_beekeeper_beekeeper_module_ts.js.map