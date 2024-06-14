"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_annual-inventory-declaration_annual-inventory-declaration_module_ts"],{

/***/ 56827:
/*!****************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration-update.ts ***!
  \****************************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclarationUpdatePage": () => (/* binding */ AnnualInventoryDeclarationUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _annual_inventory_declaration_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./annual-inventory-declaration-update.html?ngResource */ 93432);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _annual_inventory_declaration_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./annual-inventory-declaration.model */ 66937);
/* harmony import */ var _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./annual-inventory-declaration.service */ 21619);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../apiary */ 50180);
/* harmony import */ var _beekeeper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../beekeeper */ 1439);
/* harmony import */ var _apiary_information__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../apiary-information */ 4525);











let AnnualInventoryDeclarationUpdatePage = class AnnualInventoryDeclarationUpdatePage {
    constructor(beekeeperService, apiaryService, activatedRoute, navController, formBuilder, platform, toastCtrl, annualInventoryDeclarationService) {
        this.beekeeperService = beekeeperService;
        this.apiaryService = apiaryService;
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.annualInventoryDeclarationService = annualInventoryDeclarationService;
        this.isSaving = false;
        this.isNew = true;
        // eslint-disable-next-line @typescript-eslint/member-ordering
        this.form = this.formBuilder.group({
            id: [null, []],
            total: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperFaxNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperCompleteName: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperNif: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            date: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperAddress: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperPostalCode: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperPhoneNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperEntityZoneResidence: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            beekeeperNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required]],
            annualInventoryDeclarationState: [null, []],
            revisionDate: [null, []],
            revisionLocation: [null, []],
            revisorSignature: [null, []],
            revisorName: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        let req;
        const beekeeperId = localStorage.getItem('beekeeperId');
        // eslint-disable-next-line prefer-const,@typescript-eslint/naming-convention
        req = { 'Id.equals': beekeeperId };
        this.beekeeperService.query(req).subscribe(data => {
            this.beekeeper = data.body[0];
            let reqApiary;
            // eslint-disable-next-line prefer-const,@typescript-eslint/naming-convention
            reqApiary = { 'beekeeperId.equals': beekeeperId };
            this.apiaryService.query(reqApiary).subscribe(dataApiary => {
                this.apiaries = dataApiary.body;
                let total = 10;
                const apiaryInformations = [];
                for (const a of this.apiaries) {
                    // eslint-disable-next-line max-len
                    apiaryInformations.push(new _apiary_information__WEBPACK_IMPORTED_MODULE_5__.ApiaryInformation(a.id, a.zoneId, a.name, 10, a.intensive, a.transhumance, 'x', 'y', 'z', 10, '?', null));
                }
                console.log('Number total=' + total);
                // eslint-disable-next-line max-len
                this.annualInventoryDeclaration = new _annual_inventory_declaration_model__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclaration(null, total, this.beekeeper.faxNumber, this.beekeeper.beekeeperCompleteName, this.beekeeper.nif, new Date(), this.beekeeper.address, this.beekeeper.postalCode, 
                // eslint-disable-next-line max-len
                this.beekeeper.phoneNumber, this.beekeeper.entityZoneResidence.toString(), this.beekeeper.beekeeperNumber, 2 /* PENDING */, null, null, null, null, apiaryInformations);
                this.activatedRoute.data.subscribe(response => {
                    this.isNew = this.annualInventoryDeclaration.id === null || this.annualInventoryDeclaration.id === undefined;
                    this.updateForm(this.annualInventoryDeclaration);
                });
            });
        });
    }
    performData() {
    }
    updateForm(annualInventoryDeclaration) {
        this.form.patchValue({
            id: annualInventoryDeclaration.id,
            total: annualInventoryDeclaration.total,
            beekeeperFaxNumber: annualInventoryDeclaration.beekeeperFaxNumber,
            beekeeperCompleteName: annualInventoryDeclaration.beekeeperCompleteName,
            beekeeperNif: annualInventoryDeclaration.beekeeperNif,
            date: this.isNew ? new Date().toISOString().split('T')[0] : annualInventoryDeclaration.date,
            beekeeperAddress: annualInventoryDeclaration.beekeeperAddress,
            beekeeperPostalCode: annualInventoryDeclaration.beekeeperPostalCode,
            beekeeperPhoneNumber: annualInventoryDeclaration.beekeeperPhoneNumber,
            beekeeperEntityZoneResidence: annualInventoryDeclaration.beekeeperEntityZoneResidence,
            beekeeperNumber: annualInventoryDeclaration.beekeeperNumber,
            annualInventoryDeclarationState: annualInventoryDeclaration.annualInventoryDeclarationState,
            revisionDate: this.isNew ? new Date().toISOString().split('T')[0] : annualInventoryDeclaration.revisionDate,
            revisionLocation: annualInventoryDeclaration.revisionLocation,
            revisorSignature: annualInventoryDeclaration.revisorSignature,
            revisorName: annualInventoryDeclaration.revisorName,
        });
    }
    save() {
        this.isSaving = true;
        const annualInventoryDeclaration = this.createFromForm();
        annualInventoryDeclaration.date = new Date(annualInventoryDeclaration.date).toISOString().split('T')[0];
        annualInventoryDeclaration.revisionDate = new Date(annualInventoryDeclaration.revisionDate).toISOString().split('T')[0];
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.annualInventoryDeclarationService.update(annualInventoryDeclaration));
        }
        else {
            this.subscribeToSaveResponse(this.annualInventoryDeclarationService.create(annualInventoryDeclaration));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    // eslint-disable-next-line @typescript-eslint/member-ordering
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({
                message: `AnnualInventoryDeclaration ${action} successfully.`,
                duration: 2000,
                position: 'middle',
            });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/apiary-information');
        });
    }
    // eslint-disable-next-line @typescript-eslint/member-ordering
    previousState() {
        window.history.back();
    }
    // eslint-disable-next-line @typescript-eslint/member-ordering
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _annual_inventory_declaration_model__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclaration()), { id: this.form.get(['id']).value, total: this.form.get(['total']).value, beekeeperFaxNumber: this.form.get(['beekeeperFaxNumber']).value, beekeeperCompleteName: this.form.get(['beekeeperCompleteName']).value, beekeeperNif: this.form.get(['beekeeperNif']).value, date: this.form.get(['date']).value, beekeeperAddress: this.form.get(['beekeeperAddress']).value, beekeeperPostalCode: this.form.get(['beekeeperPostalCode']).value, beekeeperPhoneNumber: this.form.get(['beekeeperPhoneNumber']).value, beekeeperEntityZoneResidence: this.form.get(['beekeeperEntityZoneResidence']).value, beekeeperNumber: this.form.get(['beekeeperNumber']).value, annualInventoryDeclarationState: this.form.get(['annualInventoryDeclarationState']).value, revisionDate: this.form.get(['revisionDate']).value, revisionLocation: this.form.get(['revisionLocation']).value, revisorSignature: this.form.get(['revisorSignature']).value, revisorName: this.form.get(['revisorName']).value });
    }
};
AnnualInventoryDeclarationUpdatePage.ctorParameters = () => [
    { type: _beekeeper__WEBPACK_IMPORTED_MODULE_4__.BeekeeperService },
    { type: _apiary__WEBPACK_IMPORTED_MODULE_3__.ApiaryService },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_8__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_9__.ToastController },
    { type: _annual_inventory_declaration_service__WEBPACK_IMPORTED_MODULE_2__.AnnualInventoryDeclarationService }
];
AnnualInventoryDeclarationUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_10__.Component)({
        // eslint-disable-next-line @angular-eslint/component-selector
        selector: 'page-annual-inventory-declaration-update',
        template: _annual_inventory_declaration_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], AnnualInventoryDeclarationUpdatePage);



/***/ }),

/***/ 99040:
/*!****************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration.module.ts ***!
  \****************************************************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AnnualInventoryDeclarationPageModule": () => (/* binding */ AnnualInventoryDeclarationPageModule),
/* harmony export */   "AnnualInventoryDeclarationResolve": () => (/* binding */ AnnualInventoryDeclarationResolve)
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
/* harmony import */ var _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./annual-inventory-declaration */ 88431);
/* harmony import */ var _annual_inventory_declaration_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./annual-inventory-declaration-update */ 56827);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 11602);













let AnnualInventoryDeclarationResolve = class AnnualInventoryDeclarationResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((annualInventoryDeclaration) => annualInventoryDeclaration.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclaration());
    }
};
AnnualInventoryDeclarationResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclarationService }
];
AnnualInventoryDeclarationResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], AnnualInventoryDeclarationResolve);

const routes = [
    {
        path: '',
        component: _annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclarationPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _annual_inventory_declaration_update__WEBPACK_IMPORTED_MODULE_2__.AnnualInventoryDeclarationUpdatePage,
        resolve: {
            data: AnnualInventoryDeclarationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclarationDetailPage,
        resolve: {
            data: AnnualInventoryDeclarationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _annual_inventory_declaration_update__WEBPACK_IMPORTED_MODULE_2__.AnnualInventoryDeclarationUpdatePage,
        resolve: {
            data: AnnualInventoryDeclarationResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let AnnualInventoryDeclarationPageModule = class AnnualInventoryDeclarationPageModule {
};
AnnualInventoryDeclarationPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_annual_inventory_declaration__WEBPACK_IMPORTED_MODULE_1__.AnnualInventoryDeclarationPage, _annual_inventory_declaration_update__WEBPACK_IMPORTED_MODULE_2__.AnnualInventoryDeclarationUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.AnnualInventoryDeclarationDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], AnnualInventoryDeclarationPageModule);



/***/ }),

/***/ 93432:
/*!*****************************************************************************************************************!*\
  !*** ./src/app/pages/entities/annual-inventory-declaration/annual-inventory-declaration-update.html?ngResource ***!
  \*****************************************************************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Annual Inventory Declaration</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Total</ion-label>\r\n        <ion-input type=\"number\" name=\"total\" formControlName=\"total\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Fax Number</ion-label>\r\n        <ion-input type=\"number\" name=\"beekeeperFaxNumber\" formControlName=\"beekeeperFaxNumber\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Complete Name</ion-label>\r\n        <ion-input type=\"text\" name=\"beekeeperCompleteName\" formControlName=\"beekeeperCompleteName\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Nif</ion-label>\r\n        <ion-input type=\"number\" name=\"beekeeperNif\" formControlName=\"beekeeperNif\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Date</ion-label>\r\n        <ion-datetime displayFormat=\"MM/DD/YYYY\" formControlName=\"date\" id=\"field_date\"></ion-datetime>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Address</ion-label>\r\n        <ion-input type=\"text\" name=\"beekeeperAddress\" formControlName=\"beekeeperAddress\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Postal Code</ion-label>\r\n        <ion-input type=\"text\" name=\"beekeeperPostalCode\" formControlName=\"beekeeperPostalCode\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Phone Number</ion-label>\r\n        <ion-input type=\"number\" name=\"beekeeperPhoneNumber\" formControlName=\"beekeeperPhoneNumber\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Entity Zone Residence</ion-label>\r\n        <ion-input type=\"text\" name=\"beekeeperEntityZoneResidence\" formControlName=\"beekeeperEntityZoneResidence\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Beekeeper Number</ion-label>\r\n        <ion-input type=\"number\" name=\"beekeeperNumber\" formControlName=\"beekeeperNumber\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Annual Inventory Declaration State</ion-label>\r\n        <ion-select formControlName=\"annualInventoryDeclarationState\" id=\"field_annualInventoryDeclarationState\">\r\n          <ion-select-option value=\"APPROVED\">APPROVED</ion-select-option>\r\n          <ion-select-option value=\"DECLINED\">DECLINED</ion-select-option>\r\n          <ion-select-option value=\"PENDING\">PENDING</ion-select-option>\r\n        </ion-select>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Revision Date</ion-label>\r\n        <ion-datetime displayFormat=\"MM/DD/YYYY\" formControlName=\"revisionDate\" id=\"field_revisionDate\"></ion-datetime>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Revision Location</ion-label>\r\n        <ion-input type=\"text\" name=\"revisionLocation\" formControlName=\"revisionLocation\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Revisor Signature</ion-label>\r\n        <ion-input type=\"text\" name=\"revisorSignature\" formControlName=\"revisorSignature\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Revisor Name</ion-label>\r\n        <ion-input type=\"text\" name=\"revisorName\" formControlName=\"revisorName\"></ion-input>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_annual-inventory-declaration_annual-inventory-declaration_module_ts.js.map