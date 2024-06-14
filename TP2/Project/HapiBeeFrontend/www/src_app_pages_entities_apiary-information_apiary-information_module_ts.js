"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_apiary-information_apiary-information_module_ts"],{

/***/ 82148:
/*!********************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information-update.ts ***!
  \********************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformationUpdatePage": () => (/* binding */ ApiaryInformationUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_information_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-information-update.html?ngResource */ 7574);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _apiary_information_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary-information.model */ 86514);
/* harmony import */ var _apiary_information_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary-information.service */ 70429);
/* harmony import */ var _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../annual-inventory-declaration */ 11602);









let ApiaryInformationUpdatePage = class ApiaryInformationUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, annualInventoryDeclarationService, apiaryInformationService) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.annualInventoryDeclarationService = annualInventoryDeclarationService;
        this.apiaryInformationService = apiaryInformationService;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            zoneNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            zoneName: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            numberHives: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            intensive: ['false', [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            transhumance: ['false', [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordX: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordY: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordZ: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            numberFrames: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            annualInventoryDeclarationId: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        this.annualInventoryDeclarationService.query().subscribe(data => {
            this.annualInventoryDeclarations = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.apiaryInformation = response.data;
            this.isNew = this.apiaryInformation.id === null || this.apiaryInformation.id === undefined;
            this.updateForm(this.apiaryInformation);
        });
    }
    updateForm(apiaryInformation) {
        this.form.patchValue({
            id: apiaryInformation.id,
            zoneNumber: apiaryInformation.zoneNumber,
            zoneName: apiaryInformation.zoneName,
            numberHives: apiaryInformation.numberHives,
            intensive: apiaryInformation.intensive,
            transhumance: apiaryInformation.transhumance,
            coordX: apiaryInformation.coordX,
            coordY: apiaryInformation.coordY,
            coordZ: apiaryInformation.coordZ,
            numberFrames: apiaryInformation.numberFrames,
            annualInventoryDeclarationId: apiaryInformation.annualInventoryDeclarationId,
        });
    }
    save() {
        this.isSaving = true;
        const apiaryInformation = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.apiaryInformationService.update(apiaryInformation));
        }
        else {
            this.subscribeToSaveResponse(this.apiaryInformationService.create(apiaryInformation));
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
            const toast = yield this.toastCtrl.create({ message: `ApiaryInformation ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/apiary-information');
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
        return Object.assign(Object.assign({}, new _apiary_information_model__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformation()), { id: this.form.get(['id']).value, zoneNumber: this.form.get(['zoneNumber']).value, zoneName: this.form.get(['zoneName']).value, numberHives: this.form.get(['numberHives']).value, intensive: this.form.get(['intensive']).value, transhumance: this.form.get(['transhumance']).value, coordX: this.form.get(['coordX']).value, coordY: this.form.get(['coordY']).value, coordZ: this.form.get(['coordZ']).value, numberFrames: this.form.get(['numberFrames']).value, annualInventoryDeclarationId: this.form.get(['annualInventoryDeclarationId']).value });
    }
    compareAnnualInventoryDeclaration(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackAnnualInventoryDeclarationById(index, item) {
        return item.id;
    }
};
ApiaryInformationUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.ToastController },
    { type: _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclarationService },
    { type: _apiary_information_service__WEBPACK_IMPORTED_MODULE_2__.ApiaryInformationService }
];
ApiaryInformationUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-apiary-information-update',
        template: _apiary_information_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryInformationUpdatePage);



/***/ }),

/***/ 62273:
/*!********************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information.module.ts ***!
  \********************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryInformationPageModule": () => (/* binding */ ApiaryInformationPageModule),
/* harmony export */   "ApiaryInformationResolve": () => (/* binding */ ApiaryInformationResolve)
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
/* harmony import */ var _apiary_information__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary-information */ 6887);
/* harmony import */ var _apiary_information_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary-information-update */ 82148);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 4525);













let ApiaryInformationResolve = class ApiaryInformationResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((apiaryInformation) => apiaryInformation.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.ApiaryInformation());
    }
};
ApiaryInformationResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.ApiaryInformationService }
];
ApiaryInformationResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], ApiaryInformationResolve);

const routes = [
    {
        path: '',
        component: _apiary_information__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformationPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _apiary_information_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryInformationUpdatePage,
        resolve: {
            data: ApiaryInformationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.ApiaryInformationDetailPage,
        resolve: {
            data: ApiaryInformationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _apiary_information_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryInformationUpdatePage,
        resolve: {
            data: ApiaryInformationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let ApiaryInformationPageModule = class ApiaryInformationPageModule {
};
ApiaryInformationPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_apiary_information__WEBPACK_IMPORTED_MODULE_1__.ApiaryInformationPage, _apiary_information_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryInformationUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.ApiaryInformationDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], ApiaryInformationPageModule);



/***/ }),

/***/ 7574:
/*!*********************************************************************************************!*\
  !*** ./src/app/pages/entities/apiary-information/apiary-information-update.html?ngResource ***!
  \*********************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Apiary Information</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Zone Number</ion-label>\r\n        <ion-input type=\"number\" name=\"zoneNumber\" formControlName=\"zoneNumber\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Zone Name</ion-label>\r\n        <ion-input type=\"text\" name=\"zoneName\" formControlName=\"zoneName\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Number Hives</ion-label>\r\n        <ion-input type=\"number\" name=\"numberHives\" formControlName=\"numberHives\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Intensive</ion-label>\r\n        <ion-checkbox formControlName=\"intensive\"></ion-checkbox>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Transhumance</ion-label>\r\n        <ion-checkbox formControlName=\"transhumance\"></ion-checkbox>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord X</ion-label>\r\n        <ion-input type=\"text\" name=\"coordX\" formControlName=\"coordX\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord Y</ion-label>\r\n        <ion-input type=\"text\" name=\"coordY\" formControlName=\"coordY\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord Z</ion-label>\r\n        <ion-input type=\"text\" name=\"coordZ\" formControlName=\"coordZ\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Number Frames</ion-label>\r\n        <ion-input type=\"number\" name=\"numberFrames\" formControlName=\"numberFrames\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Annual Inventory Declaration</ion-label>\r\n        <ion-select\r\n          id=\"field_annualInventoryDeclaration\"\r\n          formControlName=\"annualInventoryDeclarationId\"\r\n          [compareWith]=\"compareAnnualInventoryDeclaration\"\r\n        >\r\n          <ion-select-option [value]=\"null\"></ion-select-option>\r\n          <ion-select-option\r\n            [value]=\"annualInventoryDeclarationOption.id\"\r\n            *ngFor=\"let annualInventoryDeclarationOption of annualInventoryDeclarations; trackBy: trackAnnualInventoryDeclarationById\"\r\n            >{{annualInventoryDeclarationOption.id}}</ion-select-option\r\n          >\r\n        </ion-select>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_apiary-information_apiary-information_module_ts.js.map