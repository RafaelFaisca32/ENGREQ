(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["polyfills"],{

/***/ 7435:
/*!**************************!*\
  !*** ./src/polyfills.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _zone_flags__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./zone-flags */ 28270);
/* harmony import */ var _zone_flags__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_zone_flags__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var zone_js_dist_zone__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! zone.js/dist/zone */ 23484);
/* harmony import */ var zone_js_dist_zone__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(zone_js_dist_zone__WEBPACK_IMPORTED_MODULE_1__);
/**
 * This file includes polyfills needed by Angular and is loaded before the app.
 * You can add your own extra polyfills to this file.
 *
 * This file is divided into 2 sections:
 *   1. Browser polyfills. These are applied before loading ZoneJS and are sorted by browsers.
 *   2. Application imports. Files imported after ZoneJS that should be loaded before your main
 *      file.
 *
 * The current setup is for so-called "evergreen" browsers; the last versions of browsers that
 * automatically update themselves. This includes Safari >= 10, Chrome >= 55 (including Opera),
 * Edge >= 13 on the desktop, and iOS 10 and Chrome on mobile.
 *
 * Learn more in https://angular.io/guide/browser-support
 */
/***************************************************************************************************
 * BROWSER POLYFILLS
 */
/** IE11 requires the following for NgClass support on SVG elements */
// import 'classlist.js';  // Run `npm install --save classlist.js`.
/**
 * Web Animations `@angular/platform-browser/animations`
 * Only required if AnimationBuilder is used within the application and using IE/Edge or Safari.
 * Standard animation support in Angular DOES NOT require any polyfills (as of Angular 6.0).
 */
// import 'web-animations-js';  // Run `npm install --save web-animations-js`.
/**
 * By default, zone.js will patch all possible macroTask and DomEvents
 * user can disable parts of macroTask/DomEvents patch by setting following flags
 * because those flags need to be set before `zone.js` being loaded, and webpack
 * will put import in the top of bundle, so user need to create a separate file
 * in this directory (for example: zone-flags.ts), and put the following flags
 * into that file, and then add the following code before importing zone.js.
 * import './zone-flags';
 *
 * The flags allowed in zone-flags.ts are listed here.
 *
 * The following flags will work for all browsers.
 *
 * (window as any).__Zone_disable_requestAnimationFrame = true; // disable patch requestAnimationFrame
 * (window as any).__Zone_disable_on_property = true; // disable patch onProperty such as onclick
 * (window as any).__zone_symbol__UNPATCHED_EVENTS = ['scroll', 'mousemove']; // disable patch specified eventNames
 *
 *  in IE/Edge developer tools, the addEventListener will also be wrapped by zone.js
 *  with the following flag, it will bypass `zone.js` patch for IE/Edge
 *
 *  (window as any).__Zone_enable_cross_context_check = true;
 *
 */

/***************************************************************************************************
 * Zone JS is required by default for Angular itself.
 */
 // Included with Angular CLI.
/***************************************************************************************************
 * APPLICATION IMPORTS
 */


/***/ }),

/***/ 28270:
/*!***************************!*\
  !*** ./src/zone-flags.ts ***!
  \***************************/
/***/ (() => {

/**
 * Prevents Angular change detection from
 * running with certain Web Component callbacks
 */
// eslint-disable-next-line no-underscore-dangle
window.__Zone_disable_customElements = true;


/***/ }),

/***/ 23484:
/*!*******************************************!*\
  !*** ./node_modules/zone.js/dist/zone.js ***!
  \*******************************************/
/***/ (function(module, exports, __webpack_require__) {

"use strict";
var __WEBPACK_AMD_DEFINE_FACTORY__, __WEBPACK_AMD_DEFINE_RESULT__;
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
/**
 * @license Angular v14.2.0-next.0
 * (c) 2010-2022 Google LLC. https://angular.io/
 * License: MIT
 */
(function (factory) {
     true ? !(__WEBPACK_AMD_DEFINE_FACTORY__ = (factory),
		__WEBPACK_AMD_DEFINE_RESULT__ = (typeof __WEBPACK_AMD_DEFINE_FACTORY__ === 'function' ?
		(__WEBPACK_AMD_DEFINE_FACTORY__.call(exports, __webpack_require__, exports, module)) :
		__WEBPACK_AMD_DEFINE_FACTORY__),
		__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__)) :
        0;
})((function () {
    'use strict';
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    ((function (global) {
        var performance = global['performance'];
        function mark(name) {
            performance && performance['mark'] && performance['mark'](name);
        }
        function performanceMeasure(name, label) {
            performance && performance['measure'] && performance['measure'](name, label);
        }
        mark('Zone');
        // Initialize before it's accessed below.
        // __Zone_symbol_prefix global can be used to override the default zone
        // symbol prefix with a custom one if needed.
        var symbolPrefix = global['__Zone_symbol_prefix'] || '__zone_symbol__';
        function __symbol__(name) {
            return symbolPrefix + name;
        }
        var checkDuplicate = global[__symbol__('forceDuplicateZoneCheck')] === true;
        if (global['Zone']) {
            // if global['Zone'] already exists (maybe zone.js was already loaded or
            // some other lib also registered a global object named Zone), we may need
            // to throw an error, but sometimes user may not want this error.
            // For example,
            // we have two web pages, page1 includes zone.js, page2 doesn't.
            // and the 1st time user load page1 and page2, everything work fine,
            // but when user load page2 again, error occurs because global['Zone'] already exists.
            // so we add a flag to let user choose whether to throw this error or not.
            // By default, if existing Zone is from zone.js, we will not throw the error.
            if (checkDuplicate || typeof global['Zone'].__symbol__ !== 'function') {
                throw new Error('Zone already loaded.');
            }
            else {
                return global['Zone'];
            }
        }
        var Zone = /** @class */ (function () {
            function Zone(parent, zoneSpec) {
                this._parent = parent;
                this._name = zoneSpec ? zoneSpec.name || 'unnamed' : '<root>';
                this._properties = zoneSpec && zoneSpec.properties || {};
                this._zoneDelegate =
                    new _ZoneDelegate(this, this._parent && this._parent._zoneDelegate, zoneSpec);
            }
            Zone.assertZonePatched = function () {
                if (global['Promise'] !== patches['ZoneAwarePromise']) {
                    throw new Error('Zone.js has detected that ZoneAwarePromise `(window|global).Promise` ' +
                        'has been overwritten.\n' +
                        'Most likely cause is that a Promise polyfill has been loaded ' +
                        'after Zone.js (Polyfilling Promise api is not necessary when zone.js is loaded. ' +
                        'If you must load one, do so before loading zone.js.)');
                }
            };
            Object.defineProperty(Zone, "root", {
                get: function () {
                    var zone = Zone.current;
                    while (zone.parent) {
                        zone = zone.parent;
                    }
                    return zone;
                },
                enumerable: false,
                configurable: true
            });
            Object.defineProperty(Zone, "current", {
                get: function () {
                    return _currentZoneFrame.zone;
                },
                enumerable: false,
                configurable: true
            });
            Object.defineProperty(Zone, "currentTask", {
                get: function () {
                    return _currentTask;
                },
                enumerable: false,
                configurable: true
            });
            // tslint:disable-next-line:require-internal-with-underscore
            Zone.__load_patch = function (name, fn, ignoreDuplicate) {
                if (ignoreDuplicate === void 0) { ignoreDuplicate = false; }
                if (patches.hasOwnProperty(name)) {
                    // `checkDuplicate` option is defined from global variable
                    // so it works for all modules.
                    // `ignoreDuplicate` can work for the specified module
                    if (!ignoreDuplicate && checkDuplicate) {
                        throw Error('Already loaded patch: ' + name);
                    }
                }
                else if (!global['__Zone_disable_' + name]) {
                    var perfName = 'Zone:' + name;
                    mark(perfName);
                    patches[name] = fn(global, Zone, _api);
                    performanceMeasure(perfName, perfName);
                }
            };
            Object.defineProperty(Zone.prototype, "parent", {
                get: function () {
                    return this._parent;
                },
                enumerable: false,
                configurable: true
            });
            Object.defineProperty(Zone.prototype, "name", {
                get: function () {
                    return this._name;
                },
                enumerable: false,
                configurable: true
            });
            Zone.prototype.get = function (key) {
                var zone = this.getZoneWith(key);
                if (zone)
                    return zone._properties[key];
            };
            Zone.prototype.getZoneWith = function (key) {
                var current = this;
                while (current) {
                    if (current._properties.hasOwnProperty(key)) {
                        return current;
                    }
                    current = current._parent;
                }
                return null;
            };
            Zone.prototype.fork = function (zoneSpec) {
                if (!zoneSpec)
                    throw new Error('ZoneSpec required!');
                return this._zoneDelegate.fork(this, zoneSpec);
            };
            Zone.prototype.wrap = function (callback, source) {
                if (typeof callback !== 'function') {
                    throw new Error('Expecting function got: ' + callback);
                }
                var _callback = this._zoneDelegate.intercept(this, callback, source);
                var zone = this;
                return function () {
                    return zone.runGuarded(_callback, this, arguments, source);
                };
            };
            Zone.prototype.run = function (callback, applyThis, applyArgs, source) {
                _currentZoneFrame = { parent: _currentZoneFrame, zone: this };
                try {
                    return this._zoneDelegate.invoke(this, callback, applyThis, applyArgs, source);
                }
                finally {
                    _currentZoneFrame = _currentZoneFrame.parent;
                }
            };
            Zone.prototype.runGuarded = function (callback, applyThis, applyArgs, source) {
                if (applyThis === void 0) { applyThis = null; }
                _currentZoneFrame = { parent: _currentZoneFrame, zone: this };
                try {
                    try {
                        return this._zoneDelegate.invoke(this, callback, applyThis, applyArgs, source);
                    }
                    catch (error) {
                        if (this._zoneDelegate.handleError(this, error)) {
                            throw error;
                        }
                    }
                }
                finally {
                    _currentZoneFrame = _currentZoneFrame.parent;
                }
            };
            Zone.prototype.runTask = function (task, applyThis, applyArgs) {
                if (task.zone != this) {
                    throw new Error('A task can only be run in the zone of creation! (Creation: ' +
                        (task.zone || NO_ZONE).name + '; Execution: ' + this.name + ')');
                }
                // https://github.com/angular/zone.js/issues/778, sometimes eventTask
                // will run in notScheduled(canceled) state, we should not try to
                // run such kind of task but just return
                if (task.state === notScheduled && (task.type === eventTask || task.type === macroTask)) {
                    return;
                }
                var reEntryGuard = task.state != running;
                reEntryGuard && task._transitionTo(running, scheduled);
                task.runCount++;
                var previousTask = _currentTask;
                _currentTask = task;
                _currentZoneFrame = { parent: _currentZoneFrame, zone: this };
                try {
                    if (task.type == macroTask && task.data && !task.data.isPeriodic) {
                        task.cancelFn = undefined;
                    }
                    try {
                        return this._zoneDelegate.invokeTask(this, task, applyThis, applyArgs);
                    }
                    catch (error) {
                        if (this._zoneDelegate.handleError(this, error)) {
                            throw error;
                        }
                    }
                }
                finally {
                    // if the task's state is notScheduled or unknown, then it has already been cancelled
                    // we should not reset the state to scheduled
                    if (task.state !== notScheduled && task.state !== unknown) {
                        if (task.type == eventTask || (task.data && task.data.isPeriodic)) {
                            reEntryGuard && task._transitionTo(scheduled, running);
                        }
                        else {
                            task.runCount = 0;
                            this._updateTaskCount(task, -1);
                            reEntryGuard &&
                                task._transitionTo(notScheduled, running, notScheduled);
                        }
                    }
                    _currentZoneFrame = _currentZoneFrame.parent;
                    _currentTask = previousTask;
                }
            };
            Zone.prototype.scheduleTask = function (task) {
                if (task.zone && task.zone !== this) {
                    // check if the task was rescheduled, the newZone
                    // should not be the children of the original zone
                    var newZone = this;
                    while (newZone) {
                        if (newZone === task.zone) {
                            throw Error("can not reschedule task to ".concat(this.name, " which is descendants of the original zone ").concat(task.zone.name));
                        }
                        newZone = newZone.parent;
                    }
                }
                task._transitionTo(scheduling, notScheduled);
                var zoneDelegates = [];
                task._zoneDelegates = zoneDelegates;
                task._zone = this;
                try {
                    task = this._zoneDelegate.scheduleTask(this, task);
                }
                catch (err) {
                    // should set task's state to unknown when scheduleTask throw error
                    // because the err may from reschedule, so the fromState maybe notScheduled
                    task._transitionTo(unknown, scheduling, notScheduled);
                    // TODO: @JiaLiPassion, should we check the result from handleError?
                    this._zoneDelegate.handleError(this, err);
                    throw err;
                }
                if (task._zoneDelegates === zoneDelegates) {
                    // we have to check because internally the delegate can reschedule the task.
                    this._updateTaskCount(task, 1);
                }
                if (task.state == scheduling) {
                    task._transitionTo(scheduled, scheduling);
                }
                return task;
            };
            Zone.prototype.scheduleMicroTask = function (source, callback, data, customSchedule) {
                return this.scheduleTask(new ZoneTask(microTask, source, callback, data, customSchedule, undefined));
            };
            Zone.prototype.scheduleMacroTask = function (source, callback, data, customSchedule, customCancel) {
                return this.scheduleTask(new ZoneTask(macroTask, source, callback, data, customSchedule, customCancel));
            };
            Zone.prototype.scheduleEventTask = function (source, callback, data, customSchedule, customCancel) {
                return this.scheduleTask(new ZoneTask(eventTask, source, callback, data, customSchedule, customCancel));
            };
            Zone.prototype.cancelTask = function (task) {
                if (task.zone != this)
                    throw new Error('A task can only be cancelled in the zone of creation! (Creation: ' +
                        (task.zone || NO_ZONE).name + '; Execution: ' + this.name + ')');
                task._transitionTo(canceling, scheduled, running);
                try {
                    this._zoneDelegate.cancelTask(this, task);
                }
                catch (err) {
                    // if error occurs when cancelTask, transit the state to unknown
                    task._transitionTo(unknown, canceling);
                    this._zoneDelegate.handleError(this, err);
                    throw err;
                }
                this._updateTaskCount(task, -1);
                task._transitionTo(notScheduled, canceling);
                task.runCount = 0;
                return task;
            };
            Zone.prototype._updateTaskCount = function (task, count) {
                var zoneDelegates = task._zoneDelegates;
                if (count == -1) {
                    task._zoneDelegates = null;
                }
                for (var i = 0; i < zoneDelegates.length; i++) {
                    zoneDelegates[i]._updateTaskCount(task.type, count);
                }
            };
            return Zone;
        }());
        // tslint:disable-next-line:require-internal-with-underscore
        Zone.__symbol__ = __symbol__;
        var DELEGATE_ZS = {
            name: '',
            onHasTask: function (delegate, _, target, hasTaskState) { return delegate.hasTask(target, hasTaskState); },
            onScheduleTask: function (delegate, _, target, task) { return delegate.scheduleTask(target, task); },
            onInvokeTask: function (delegate, _, target, task, applyThis, applyArgs) { return delegate.invokeTask(target, task, applyThis, applyArgs); },
            onCancelTask: function (delegate, _, target, task) { return delegate.cancelTask(target, task); }
        };
        var _ZoneDelegate = /** @class */ (function () {
            function _ZoneDelegate(zone, parentDelegate, zoneSpec) {
                this._taskCounts = { 'microTask': 0, 'macroTask': 0, 'eventTask': 0 };
                this.zone = zone;
                this._parentDelegate = parentDelegate;
                this._forkZS = zoneSpec && (zoneSpec && zoneSpec.onFork ? zoneSpec : parentDelegate._forkZS);
                this._forkDlgt = zoneSpec && (zoneSpec.onFork ? parentDelegate : parentDelegate._forkDlgt);
                this._forkCurrZone =
                    zoneSpec && (zoneSpec.onFork ? this.zone : parentDelegate._forkCurrZone);
                this._interceptZS =
                    zoneSpec && (zoneSpec.onIntercept ? zoneSpec : parentDelegate._interceptZS);
                this._interceptDlgt =
                    zoneSpec && (zoneSpec.onIntercept ? parentDelegate : parentDelegate._interceptDlgt);
                this._interceptCurrZone =
                    zoneSpec && (zoneSpec.onIntercept ? this.zone : parentDelegate._interceptCurrZone);
                this._invokeZS = zoneSpec && (zoneSpec.onInvoke ? zoneSpec : parentDelegate._invokeZS);
                this._invokeDlgt =
                    zoneSpec && (zoneSpec.onInvoke ? parentDelegate : parentDelegate._invokeDlgt);
                this._invokeCurrZone =
                    zoneSpec && (zoneSpec.onInvoke ? this.zone : parentDelegate._invokeCurrZone);
                this._handleErrorZS =
                    zoneSpec && (zoneSpec.onHandleError ? zoneSpec : parentDelegate._handleErrorZS);
                this._handleErrorDlgt =
                    zoneSpec && (zoneSpec.onHandleError ? parentDelegate : parentDelegate._handleErrorDlgt);
                this._handleErrorCurrZone =
                    zoneSpec && (zoneSpec.onHandleError ? this.zone : parentDelegate._handleErrorCurrZone);
                this._scheduleTaskZS =
                    zoneSpec && (zoneSpec.onScheduleTask ? zoneSpec : parentDelegate._scheduleTaskZS);
                this._scheduleTaskDlgt = zoneSpec &&
                    (zoneSpec.onScheduleTask ? parentDelegate : parentDelegate._scheduleTaskDlgt);
                this._scheduleTaskCurrZone =
                    zoneSpec && (zoneSpec.onScheduleTask ? this.zone : parentDelegate._scheduleTaskCurrZone);
                this._invokeTaskZS =
                    zoneSpec && (zoneSpec.onInvokeTask ? zoneSpec : parentDelegate._invokeTaskZS);
                this._invokeTaskDlgt =
                    zoneSpec && (zoneSpec.onInvokeTask ? parentDelegate : parentDelegate._invokeTaskDlgt);
                this._invokeTaskCurrZone =
                    zoneSpec && (zoneSpec.onInvokeTask ? this.zone : parentDelegate._invokeTaskCurrZone);
                this._cancelTaskZS =
                    zoneSpec && (zoneSpec.onCancelTask ? zoneSpec : parentDelegate._cancelTaskZS);
                this._cancelTaskDlgt =
                    zoneSpec && (zoneSpec.onCancelTask ? parentDelegate : parentDelegate._cancelTaskDlgt);
                this._cancelTaskCurrZone =
                    zoneSpec && (zoneSpec.onCancelTask ? this.zone : parentDelegate._cancelTaskCurrZone);
                this._hasTaskZS = null;
                this._hasTaskDlgt = null;
                this._hasTaskDlgtOwner = null;
                this._hasTaskCurrZone = null;
                var zoneSpecHasTask = zoneSpec && zoneSpec.onHasTask;
                var parentHasTask = parentDelegate && parentDelegate._hasTaskZS;
                if (zoneSpecHasTask || parentHasTask) {
                    // If we need to report hasTask, than this ZS needs to do ref counting on tasks. In such
                    // a case all task related interceptors must go through this ZD. We can't short circuit it.
                    this._hasTaskZS = zoneSpecHasTask ? zoneSpec : DELEGATE_ZS;
                    this._hasTaskDlgt = parentDelegate;
                    this._hasTaskDlgtOwner = this;
                    this._hasTaskCurrZone = zone;
                    if (!zoneSpec.onScheduleTask) {
                        this._scheduleTaskZS = DELEGATE_ZS;
                        this._scheduleTaskDlgt = parentDelegate;
                        this._scheduleTaskCurrZone = this.zone;
                    }
                    if (!zoneSpec.onInvokeTask) {
                        this._invokeTaskZS = DELEGATE_ZS;
                        this._invokeTaskDlgt = parentDelegate;
                        this._invokeTaskCurrZone = this.zone;
                    }
                    if (!zoneSpec.onCancelTask) {
                        this._cancelTaskZS = DELEGATE_ZS;
                        this._cancelTaskDlgt = parentDelegate;
                        this._cancelTaskCurrZone = this.zone;
                    }
                }
            }
            _ZoneDelegate.prototype.fork = function (targetZone, zoneSpec) {
                return this._forkZS ? this._forkZS.onFork(this._forkDlgt, this.zone, targetZone, zoneSpec) :
                    new Zone(targetZone, zoneSpec);
            };
            _ZoneDelegate.prototype.intercept = function (targetZone, callback, source) {
                return this._interceptZS ?
                    this._interceptZS.onIntercept(this._interceptDlgt, this._interceptCurrZone, targetZone, callback, source) :
                    callback;
            };
            _ZoneDelegate.prototype.invoke = function (targetZone, callback, applyThis, applyArgs, source) {
                return this._invokeZS ? this._invokeZS.onInvoke(this._invokeDlgt, this._invokeCurrZone, targetZone, callback, applyThis, applyArgs, source) :
                    callback.apply(applyThis, applyArgs);
            };
            _ZoneDelegate.prototype.handleError = function (targetZone, error) {
                return this._handleErrorZS ?
                    this._handleErrorZS.onHandleError(this._handleErrorDlgt, this._handleErrorCurrZone, targetZone, error) :
                    true;
            };
            _ZoneDelegate.prototype.scheduleTask = function (targetZone, task) {
                var returnTask = task;
                if (this._scheduleTaskZS) {
                    if (this._hasTaskZS) {
                        returnTask._zoneDelegates.push(this._hasTaskDlgtOwner);
                    }
                    // clang-format off
                    returnTask = this._scheduleTaskZS.onScheduleTask(this._scheduleTaskDlgt, this._scheduleTaskCurrZone, targetZone, task);
                    // clang-format on
                    if (!returnTask)
                        returnTask = task;
                }
                else {
                    if (task.scheduleFn) {
                        task.scheduleFn(task);
                    }
                    else if (task.type == microTask) {
                        scheduleMicroTask(task);
                    }
                    else {
                        throw new Error('Task is missing scheduleFn.');
                    }
                }
                return returnTask;
            };
            _ZoneDelegate.prototype.invokeTask = function (targetZone, task, applyThis, applyArgs) {
                return this._invokeTaskZS ? this._invokeTaskZS.onInvokeTask(this._invokeTaskDlgt, this._invokeTaskCurrZone, targetZone, task, applyThis, applyArgs) :
                    task.callback.apply(applyThis, applyArgs);
            };
            _ZoneDelegate.prototype.cancelTask = function (targetZone, task) {
                var value;
                if (this._cancelTaskZS) {
                    value = this._cancelTaskZS.onCancelTask(this._cancelTaskDlgt, this._cancelTaskCurrZone, targetZone, task);
                }
                else {
                    if (!task.cancelFn) {
                        throw Error('Task is not cancelable');
                    }
                    value = task.cancelFn(task);
                }
                return value;
            };
            _ZoneDelegate.prototype.hasTask = function (targetZone, isEmpty) {
                // hasTask should not throw error so other ZoneDelegate
                // can still trigger hasTask callback
                try {
                    this._hasTaskZS &&
                        this._hasTaskZS.onHasTask(this._hasTaskDlgt, this._hasTaskCurrZone, targetZone, isEmpty);
                }
                catch (err) {
                    this.handleError(targetZone, err);
                }
            };
            // tslint:disable-next-line:require-internal-with-underscore
            _ZoneDelegate.prototype._updateTaskCount = function (type, count) {
                var counts = this._taskCounts;
                var prev = counts[type];
                var next = counts[type] = prev + count;
                if (next < 0) {
                    throw new Error('More tasks executed then were scheduled.');
                }
                if (prev == 0 || next == 0) {
                    var isEmpty = {
                        microTask: counts['microTask'] > 0,
                        macroTask: counts['macroTask'] > 0,
                        eventTask: counts['eventTask'] > 0,
                        change: type
                    };
                    this.hasTask(this.zone, isEmpty);
                }
            };
            return _ZoneDelegate;
        }());
        var ZoneTask = /** @class */ (function () {
            function ZoneTask(type, source, callback, options, scheduleFn, cancelFn) {
                // tslint:disable-next-line:require-internal-with-underscore
                this._zone = null;
                this.runCount = 0;
                // tslint:disable-next-line:require-internal-with-underscore
                this._zoneDelegates = null;
                // tslint:disable-next-line:require-internal-with-underscore
                this._state = 'notScheduled';
                this.type = type;
                this.source = source;
                this.data = options;
                this.scheduleFn = scheduleFn;
                this.cancelFn = cancelFn;
                if (!callback) {
                    throw new Error('callback is not defined');
                }
                this.callback = callback;
                var self = this;
                // TODO: @JiaLiPassion options should have interface
                if (type === eventTask && options && options.useG) {
                    this.invoke = ZoneTask.invokeTask;
                }
                else {
                    this.invoke = function () {
                        return ZoneTask.invokeTask.call(global, self, this, arguments);
                    };
                }
            }
            ZoneTask.invokeTask = function (task, target, args) {
                if (!task) {
                    task = this;
                }
                _numberOfNestedTaskFrames++;
                try {
                    task.runCount++;
                    return task.zone.runTask(task, target, args);
                }
                finally {
                    if (_numberOfNestedTaskFrames == 1) {
                        drainMicroTaskQueue();
                    }
                    _numberOfNestedTaskFrames--;
                }
            };
            Object.defineProperty(ZoneTask.prototype, "zone", {
                get: function () {
                    return this._zone;
                },
                enumerable: false,
                configurable: true
            });
            Object.defineProperty(ZoneTask.prototype, "state", {
                get: function () {
                    return this._state;
                },
                enumerable: false,
                configurable: true
            });
            ZoneTask.prototype.cancelScheduleRequest = function () {
                this._transitionTo(notScheduled, scheduling);
            };
            // tslint:disable-next-line:require-internal-with-underscore
            ZoneTask.prototype._transitionTo = function (toState, fromState1, fromState2) {
                if (this._state === fromState1 || this._state === fromState2) {
                    this._state = toState;
                    if (toState == notScheduled) {
                        this._zoneDelegates = null;
                    }
                }
                else {
                    throw new Error("".concat(this.type, " '").concat(this.source, "': can not transition to '").concat(toState, "', expecting state '").concat(fromState1, "'").concat(fromState2 ? ' or \'' + fromState2 + '\'' : '', ", was '").concat(this._state, "'."));
                }
            };
            ZoneTask.prototype.toString = function () {
                if (this.data && typeof this.data.handleId !== 'undefined') {
                    return this.data.handleId.toString();
                }
                else {
                    return Object.prototype.toString.call(this);
                }
            };
            // add toJSON method to prevent cyclic error when
            // call JSON.stringify(zoneTask)
            ZoneTask.prototype.toJSON = function () {
                return {
                    type: this.type,
                    state: this.state,
                    source: this.source,
                    zone: this.zone.name,
                    runCount: this.runCount
                };
            };
            return ZoneTask;
        }());
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        ///  MICROTASK QUEUE
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        var symbolSetTimeout = __symbol__('setTimeout');
        var symbolPromise = __symbol__('Promise');
        var symbolThen = __symbol__('then');
        var _microTaskQueue = [];
        var _isDrainingMicrotaskQueue = false;
        var nativeMicroTaskQueuePromise;
        function nativeScheduleMicroTask(func) {
            if (!nativeMicroTaskQueuePromise) {
                if (global[symbolPromise]) {
                    nativeMicroTaskQueuePromise = global[symbolPromise].resolve(0);
                }
            }
            if (nativeMicroTaskQueuePromise) {
                var nativeThen = nativeMicroTaskQueuePromise[symbolThen];
                if (!nativeThen) {
                    // native Promise is not patchable, we need to use `then` directly
                    // issue 1078
                    nativeThen = nativeMicroTaskQueuePromise['then'];
                }
                nativeThen.call(nativeMicroTaskQueuePromise, func);
            }
            else {
                global[symbolSetTimeout](func, 0);
            }
        }
        function scheduleMicroTask(task) {
            // if we are not running in any task, and there has not been anything scheduled
            // we must bootstrap the initial task creation by manually scheduling the drain
            if (_numberOfNestedTaskFrames === 0 && _microTaskQueue.length === 0) {
                // We are not running in Task, so we need to kickstart the microtask queue.
                nativeScheduleMicroTask(drainMicroTaskQueue);
            }
            task && _microTaskQueue.push(task);
        }
        function drainMicroTaskQueue() {
            if (!_isDrainingMicrotaskQueue) {
                _isDrainingMicrotaskQueue = true;
                while (_microTaskQueue.length) {
                    var queue = _microTaskQueue;
                    _microTaskQueue = [];
                    for (var i = 0; i < queue.length; i++) {
                        var task = queue[i];
                        try {
                            task.zone.runTask(task, null, null);
                        }
                        catch (error) {
                            _api.onUnhandledError(error);
                        }
                    }
                }
                _api.microtaskDrainDone();
                _isDrainingMicrotaskQueue = false;
            }
        }
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        ///  BOOTSTRAP
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        var NO_ZONE = { name: 'NO ZONE' };
        var notScheduled = 'notScheduled', scheduling = 'scheduling', scheduled = 'scheduled', running = 'running', canceling = 'canceling', unknown = 'unknown';
        var microTask = 'microTask', macroTask = 'macroTask', eventTask = 'eventTask';
        var patches = {};
        var _api = {
            symbol: __symbol__,
            currentZoneFrame: function () { return _currentZoneFrame; },
            onUnhandledError: noop,
            microtaskDrainDone: noop,
            scheduleMicroTask: scheduleMicroTask,
            showUncaughtError: function () { return !Zone[__symbol__('ignoreConsoleErrorUncaughtError')]; },
            patchEventTarget: function () { return []; },
            patchOnProperties: noop,
            patchMethod: function () { return noop; },
            bindArguments: function () { return []; },
            patchThen: function () { return noop; },
            patchMacroTask: function () { return noop; },
            patchEventPrototype: function () { return noop; },
            isIEOrEdge: function () { return false; },
            getGlobalObjects: function () { return undefined; },
            ObjectDefineProperty: function () { return noop; },
            ObjectGetOwnPropertyDescriptor: function () { return undefined; },
            ObjectCreate: function () { return undefined; },
            ArraySlice: function () { return []; },
            patchClass: function () { return noop; },
            wrapWithCurrentZone: function () { return noop; },
            filterProperties: function () { return []; },
            attachOriginToPatched: function () { return noop; },
            _redefineProperty: function () { return noop; },
            patchCallbacks: function () { return noop; },
            nativeScheduleMicroTask: nativeScheduleMicroTask
        };
        var _currentZoneFrame = { parent: null, zone: new Zone(null, null) };
        var _currentTask = null;
        var _numberOfNestedTaskFrames = 0;
        function noop() { }
        performanceMeasure('Zone', 'Zone');
        return global['Zone'] = Zone;
    }))(typeof window !== 'undefined' && window || typeof self !== 'undefined' && self || global);
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    /**
     * Suppress closure compiler errors about unknown 'Zone' variable
     * @fileoverview
     * @suppress {undefinedVars,globalThis,missingRequire}
     */
    /// <reference types="node"/>
    // issue #989, to reduce bundle size, use short name
    /** Object.getOwnPropertyDescriptor */
    var ObjectGetOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;
    /** Object.defineProperty */
    var ObjectDefineProperty = Object.defineProperty;
    /** Object.getPrototypeOf */
    var ObjectGetPrototypeOf = Object.getPrototypeOf;
    /** Object.create */
    var ObjectCreate = Object.create;
    /** Array.prototype.slice */
    var ArraySlice = Array.prototype.slice;
    /** addEventListener string const */
    var ADD_EVENT_LISTENER_STR = 'addEventListener';
    /** removeEventListener string const */
    var REMOVE_EVENT_LISTENER_STR = 'removeEventListener';
    /** zoneSymbol addEventListener */
    var ZONE_SYMBOL_ADD_EVENT_LISTENER = Zone.__symbol__(ADD_EVENT_LISTENER_STR);
    /** zoneSymbol removeEventListener */
    var ZONE_SYMBOL_REMOVE_EVENT_LISTENER = Zone.__symbol__(REMOVE_EVENT_LISTENER_STR);
    /** true string const */
    var TRUE_STR = 'true';
    /** false string const */
    var FALSE_STR = 'false';
    /** Zone symbol prefix string const. */
    var ZONE_SYMBOL_PREFIX = Zone.__symbol__('');
    function wrapWithCurrentZone(callback, source) {
        return Zone.current.wrap(callback, source);
    }
    function scheduleMacroTaskWithCurrentZone(source, callback, data, customSchedule, customCancel) {
        return Zone.current.scheduleMacroTask(source, callback, data, customSchedule, customCancel);
    }
    var zoneSymbol$1 = Zone.__symbol__;
    var isWindowExists = typeof window !== 'undefined';
    var internalWindow = isWindowExists ? window : undefined;
    var _global = isWindowExists && internalWindow || typeof self === 'object' && self || global;
    var REMOVE_ATTRIBUTE = 'removeAttribute';
    function bindArguments(args, source) {
        for (var i = args.length - 1; i >= 0; i--) {
            if (typeof args[i] === 'function') {
                args[i] = wrapWithCurrentZone(args[i], source + '_' + i);
            }
        }
        return args;
    }
    function patchPrototype(prototype, fnNames) {
        var source = prototype.constructor['name'];
        var _loop_1 = function (i) {
            var name_1 = fnNames[i];
            var delegate = prototype[name_1];
            if (delegate) {
                var prototypeDesc = ObjectGetOwnPropertyDescriptor(prototype, name_1);
                if (!isPropertyWritable(prototypeDesc)) {
                    return "continue";
                }
                prototype[name_1] = (function (delegate) {
                    var patched = function () {
                        return delegate.apply(this, bindArguments(arguments, source + '.' + name_1));
                    };
                    attachOriginToPatched(patched, delegate);
                    return patched;
                })(delegate);
            }
        };
        for (var i = 0; i < fnNames.length; i++) {
            _loop_1(i);
        }
    }
    function isPropertyWritable(propertyDesc) {
        if (!propertyDesc) {
            return true;
        }
        if (propertyDesc.writable === false) {
            return false;
        }
        return !(typeof propertyDesc.get === 'function' && typeof propertyDesc.set === 'undefined');
    }
    var isWebWorker = (typeof WorkerGlobalScope !== 'undefined' && self instanceof WorkerGlobalScope);
    // Make sure to access `process` through `_global` so that WebPack does not accidentally browserify
    // this code.
    var isNode = (!('nw' in _global) && typeof _global.process !== 'undefined' &&
        {}.toString.call(_global.process) === '[object process]');
    var isBrowser = !isNode && !isWebWorker && !!(isWindowExists && internalWindow['HTMLElement']);
    // we are in electron of nw, so we are both browser and nodejs
    // Make sure to access `process` through `_global` so that WebPack does not accidentally browserify
    // this code.
    var isMix = typeof _global.process !== 'undefined' &&
        {}.toString.call(_global.process) === '[object process]' && !isWebWorker &&
        !!(isWindowExists && internalWindow['HTMLElement']);
    var zoneSymbolEventNames$1 = {};
    var wrapFn = function (event) {
        // https://github.com/angular/zone.js/issues/911, in IE, sometimes
        // event will be undefined, so we need to use window.event
        event = event || _global.event;
        if (!event) {
            return;
        }
        var eventNameSymbol = zoneSymbolEventNames$1[event.type];
        if (!eventNameSymbol) {
            eventNameSymbol = zoneSymbolEventNames$1[event.type] = zoneSymbol$1('ON_PROPERTY' + event.type);
        }
        var target = this || event.target || _global;
        var listener = target[eventNameSymbol];
        var result;
        if (isBrowser && target === internalWindow && event.type === 'error') {
            // window.onerror have different signature
            // https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onerror#window.onerror
            // and onerror callback will prevent default when callback return true
            var errorEvent = event;
            result = listener &&
                listener.call(this, errorEvent.message, errorEvent.filename, errorEvent.lineno, errorEvent.colno, errorEvent.error);
            if (result === true) {
                event.preventDefault();
            }
        }
        else {
            result = listener && listener.apply(this, arguments);
            if (result != undefined && !result) {
                event.preventDefault();
            }
        }
        return result;
    };
    function patchProperty(obj, prop, prototype) {
        var desc = ObjectGetOwnPropertyDescriptor(obj, prop);
        if (!desc && prototype) {
            // when patch window object, use prototype to check prop exist or not
            var prototypeDesc = ObjectGetOwnPropertyDescriptor(prototype, prop);
            if (prototypeDesc) {
                desc = { enumerable: true, configurable: true };
            }
        }
        // if the descriptor not exists or is not configurable
        // just return
        if (!desc || !desc.configurable) {
            return;
        }
        var onPropPatchedSymbol = zoneSymbol$1('on' + prop + 'patched');
        if (obj.hasOwnProperty(onPropPatchedSymbol) && obj[onPropPatchedSymbol]) {
            return;
        }
        // A property descriptor cannot have getter/setter and be writable
        // deleting the writable and value properties avoids this error:
        //
        // TypeError: property descriptors must not specify a value or be writable when a
        // getter or setter has been specified
        delete desc.writable;
        delete desc.value;
        var originalDescGet = desc.get;
        var originalDescSet = desc.set;
        // slice(2) cuz 'onclick' -> 'click', etc
        var eventName = prop.slice(2);
        var eventNameSymbol = zoneSymbolEventNames$1[eventName];
        if (!eventNameSymbol) {
            eventNameSymbol = zoneSymbolEventNames$1[eventName] = zoneSymbol$1('ON_PROPERTY' + eventName);
        }
        desc.set = function (newValue) {
            // in some of windows's onproperty callback, this is undefined
            // so we need to check it
            var target = this;
            if (!target && obj === _global) {
                target = _global;
            }
            if (!target) {
                return;
            }
            var previousValue = target[eventNameSymbol];
            if (typeof previousValue === 'function') {
                target.removeEventListener(eventName, wrapFn);
            }
            // issue #978, when onload handler was added before loading zone.js
            // we should remove it with originalDescSet
            originalDescSet && originalDescSet.call(target, null);
            target[eventNameSymbol] = newValue;
            if (typeof newValue === 'function') {
                target.addEventListener(eventName, wrapFn, false);
            }
        };
        // The getter would return undefined for unassigned properties but the default value of an
        // unassigned property is null
        desc.get = function () {
            // in some of windows's onproperty callback, this is undefined
            // so we need to check it
            var target = this;
            if (!target && obj === _global) {
                target = _global;
            }
            if (!target) {
                return null;
            }
            var listener = target[eventNameSymbol];
            if (listener) {
                return listener;
            }
            else if (originalDescGet) {
                // result will be null when use inline event attribute,
                // such as <button onclick="func();">OK</button>
                // because the onclick function is internal raw uncompiled handler
                // the onclick will be evaluated when first time event was triggered or
                // the property is accessed, https://github.com/angular/zone.js/issues/525
                // so we should use original native get to retrieve the handler
                var value = originalDescGet.call(this);
                if (value) {
                    desc.set.call(this, value);
                    if (typeof target[REMOVE_ATTRIBUTE] === 'function') {
                        target.removeAttribute(prop);
                    }
                    return value;
                }
            }
            return null;
        };
        ObjectDefineProperty(obj, prop, desc);
        obj[onPropPatchedSymbol] = true;
    }
    function patchOnProperties(obj, properties, prototype) {
        if (properties) {
            for (var i = 0; i < properties.length; i++) {
                patchProperty(obj, 'on' + properties[i], prototype);
            }
        }
        else {
            var onProperties = [];
            for (var prop in obj) {
                if (prop.slice(0, 2) == 'on') {
                    onProperties.push(prop);
                }
            }
            for (var j = 0; j < onProperties.length; j++) {
                patchProperty(obj, onProperties[j], prototype);
            }
        }
    }
    var originalInstanceKey = zoneSymbol$1('originalInstance');
    // wrap some native API on `window`
    function patchClass(className) {
        var OriginalClass = _global[className];
        if (!OriginalClass)
            return;
        // keep original class in global
        _global[zoneSymbol$1(className)] = OriginalClass;
        _global[className] = function () {
            var a = bindArguments(arguments, className);
            switch (a.length) {
                case 0:
                    this[originalInstanceKey] = new OriginalClass();
                    break;
                case 1:
                    this[originalInstanceKey] = new OriginalClass(a[0]);
                    break;
                case 2:
                    this[originalInstanceKey] = new OriginalClass(a[0], a[1]);
                    break;
                case 3:
                    this[originalInstanceKey] = new OriginalClass(a[0], a[1], a[2]);
                    break;
                case 4:
                    this[originalInstanceKey] = new OriginalClass(a[0], a[1], a[2], a[3]);
                    break;
                default:
                    throw new Error('Arg list too long.');
            }
        };
        // attach original delegate to patched function
        attachOriginToPatched(_global[className], OriginalClass);
        var instance = new OriginalClass(function () { });
        var prop;
        for (prop in instance) {
            // https://bugs.webkit.org/show_bug.cgi?id=44721
            if (className === 'XMLHttpRequest' && prop === 'responseBlob')
                continue;
            (function (prop) {
                if (typeof instance[prop] === 'function') {
                    _global[className].prototype[prop] = function () {
                        return this[originalInstanceKey][prop].apply(this[originalInstanceKey], arguments);
                    };
                }
                else {
                    ObjectDefineProperty(_global[className].prototype, prop, {
                        set: function (fn) {
                            if (typeof fn === 'function') {
                                this[originalInstanceKey][prop] = wrapWithCurrentZone(fn, className + '.' + prop);
                                // keep callback in wrapped function so we can
                                // use it in Function.prototype.toString to return
                                // the native one.
                                attachOriginToPatched(this[originalInstanceKey][prop], fn);
                            }
                            else {
                                this[originalInstanceKey][prop] = fn;
                            }
                        },
                        get: function () {
                            return this[originalInstanceKey][prop];
                        }
                    });
                }
            }(prop));
        }
        for (prop in OriginalClass) {
            if (prop !== 'prototype' && OriginalClass.hasOwnProperty(prop)) {
                _global[className][prop] = OriginalClass[prop];
            }
        }
    }
    function patchMethod(target, name, patchFn) {
        var proto = target;
        while (proto && !proto.hasOwnProperty(name)) {
            proto = ObjectGetPrototypeOf(proto);
        }
        if (!proto && target[name]) {
            // somehow we did not find it, but we can see it. This happens on IE for Window properties.
            proto = target;
        }
        var delegateName = zoneSymbol$1(name);
        var delegate = null;
        if (proto && (!(delegate = proto[delegateName]) || !proto.hasOwnProperty(delegateName))) {
            delegate = proto[delegateName] = proto[name];
            // check whether proto[name] is writable
            // some property is readonly in safari, such as HtmlCanvasElement.prototype.toBlob
            var desc = proto && ObjectGetOwnPropertyDescriptor(proto, name);
            if (isPropertyWritable(desc)) {
                var patchDelegate_1 = patchFn(delegate, delegateName, name);
                proto[name] = function () {
                    return patchDelegate_1(this, arguments);
                };
                attachOriginToPatched(proto[name], delegate);
            }
        }
        return delegate;
    }
    // TODO: @JiaLiPassion, support cancel task later if necessary
    function patchMacroTask(obj, funcName, metaCreator) {
        var setNative = null;
        function scheduleTask(task) {
            var data = task.data;
            data.args[data.cbIdx] = function () {
                task.invoke.apply(this, arguments);
            };
            setNative.apply(data.target, data.args);
            return task;
        }
        setNative = patchMethod(obj, funcName, function (delegate) { return function (self, args) {
            var meta = metaCreator(self, args);
            if (meta.cbIdx >= 0 && typeof args[meta.cbIdx] === 'function') {
                return scheduleMacroTaskWithCurrentZone(meta.name, args[meta.cbIdx], meta, scheduleTask);
            }
            else {
                // cause an error by calling it directly.
                return delegate.apply(self, args);
            }
        }; });
    }
    function attachOriginToPatched(patched, original) {
        patched[zoneSymbol$1('OriginalDelegate')] = original;
    }
    var isDetectedIEOrEdge = false;
    var ieOrEdge = false;
    function isIE() {
        try {
            var ua = internalWindow.navigator.userAgent;
            if (ua.indexOf('MSIE ') !== -1 || ua.indexOf('Trident/') !== -1) {
                return true;
            }
        }
        catch (error) {
        }
        return false;
    }
    function isIEOrEdge() {
        if (isDetectedIEOrEdge) {
            return ieOrEdge;
        }
        isDetectedIEOrEdge = true;
        try {
            var ua = internalWindow.navigator.userAgent;
            if (ua.indexOf('MSIE ') !== -1 || ua.indexOf('Trident/') !== -1 || ua.indexOf('Edge/') !== -1) {
                ieOrEdge = true;
            }
        }
        catch (error) {
        }
        return ieOrEdge;
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    Zone.__load_patch('ZoneAwarePromise', function (global, Zone, api) {
        var ObjectGetOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;
        var ObjectDefineProperty = Object.defineProperty;
        function readableObjectToString(obj) {
            if (obj && obj.toString === Object.prototype.toString) {
                var className = obj.constructor && obj.constructor.name;
                return (className ? className : '') + ': ' + JSON.stringify(obj);
            }
            return obj ? obj.toString() : Object.prototype.toString.call(obj);
        }
        var __symbol__ = api.symbol;
        var _uncaughtPromiseErrors = [];
        var isDisableWrappingUncaughtPromiseRejection = global[__symbol__('DISABLE_WRAPPING_UNCAUGHT_PROMISE_REJECTION')] === true;
        var symbolPromise = __symbol__('Promise');
        var symbolThen = __symbol__('then');
        var creationTrace = '__creationTrace__';
        api.onUnhandledError = function (e) {
            if (api.showUncaughtError()) {
                var rejection = e && e.rejection;
                if (rejection) {
                    console.error('Unhandled Promise rejection:', rejection instanceof Error ? rejection.message : rejection, '; Zone:', e.zone.name, '; Task:', e.task && e.task.source, '; Value:', rejection, rejection instanceof Error ? rejection.stack : undefined);
                }
                else {
                    console.error(e);
                }
            }
        };
        api.microtaskDrainDone = function () {
            var _loop_2 = function () {
                var uncaughtPromiseError = _uncaughtPromiseErrors.shift();
                try {
                    uncaughtPromiseError.zone.runGuarded(function () {
                        if (uncaughtPromiseError.throwOriginal) {
                            throw uncaughtPromiseError.rejection;
                        }
                        throw uncaughtPromiseError;
                    });
                }
                catch (error) {
                    handleUnhandledRejection(error);
                }
            };
            while (_uncaughtPromiseErrors.length) {
                _loop_2();
            }
        };
        var UNHANDLED_PROMISE_REJECTION_HANDLER_SYMBOL = __symbol__('unhandledPromiseRejectionHandler');
        function handleUnhandledRejection(e) {
            api.onUnhandledError(e);
            try {
                var handler = Zone[UNHANDLED_PROMISE_REJECTION_HANDLER_SYMBOL];
                if (typeof handler === 'function') {
                    handler.call(this, e);
                }
            }
            catch (err) {
            }
        }
        function isThenable(value) {
            return value && value.then;
        }
        function forwardResolution(value) {
            return value;
        }
        function forwardRejection(rejection) {
            return ZoneAwarePromise.reject(rejection);
        }
        var symbolState = __symbol__('state');
        var symbolValue = __symbol__('value');
        var symbolFinally = __symbol__('finally');
        var symbolParentPromiseValue = __symbol__('parentPromiseValue');
        var symbolParentPromiseState = __symbol__('parentPromiseState');
        var source = 'Promise.then';
        var UNRESOLVED = null;
        var RESOLVED = true;
        var REJECTED = false;
        var REJECTED_NO_CATCH = 0;
        function makeResolver(promise, state) {
            return function (v) {
                try {
                    resolvePromise(promise, state, v);
                }
                catch (err) {
                    resolvePromise(promise, false, err);
                }
                // Do not return value or you will break the Promise spec.
            };
        }
        var once = function () {
            var wasCalled = false;
            return function wrapper(wrappedFunction) {
                return function () {
                    if (wasCalled) {
                        return;
                    }
                    wasCalled = true;
                    wrappedFunction.apply(null, arguments);
                };
            };
        };
        var TYPE_ERROR = 'Promise resolved with itself';
        var CURRENT_TASK_TRACE_SYMBOL = __symbol__('currentTaskTrace');
        // Promise Resolution
        function resolvePromise(promise, state, value) {
            var onceWrapper = once();
            if (promise === value) {
                throw new TypeError(TYPE_ERROR);
            }
            if (promise[symbolState] === UNRESOLVED) {
                // should only get value.then once based on promise spec.
                var then = null;
                try {
                    if (typeof value === 'object' || typeof value === 'function') {
                        then = value && value.then;
                    }
                }
                catch (err) {
                    onceWrapper(function () {
                        resolvePromise(promise, false, err);
                    })();
                    return promise;
                }
                // if (value instanceof ZoneAwarePromise) {
                if (state !== REJECTED && value instanceof ZoneAwarePromise &&
                    value.hasOwnProperty(symbolState) && value.hasOwnProperty(symbolValue) &&
                    value[symbolState] !== UNRESOLVED) {
                    clearRejectedNoCatch(value);
                    resolvePromise(promise, value[symbolState], value[symbolValue]);
                }
                else if (state !== REJECTED && typeof then === 'function') {
                    try {
                        then.call(value, onceWrapper(makeResolver(promise, state)), onceWrapper(makeResolver(promise, false)));
                    }
                    catch (err) {
                        onceWrapper(function () {
                            resolvePromise(promise, false, err);
                        })();
                    }
                }
                else {
                    promise[symbolState] = state;
                    var queue = promise[symbolValue];
                    promise[symbolValue] = value;
                    if (promise[symbolFinally] === symbolFinally) {
                        // the promise is generated by Promise.prototype.finally
                        if (state === RESOLVED) {
                            // the state is resolved, should ignore the value
                            // and use parent promise value
                            promise[symbolState] = promise[symbolParentPromiseState];
                            promise[symbolValue] = promise[symbolParentPromiseValue];
                        }
                    }
                    // record task information in value when error occurs, so we can
                    // do some additional work such as render longStackTrace
                    if (state === REJECTED && value instanceof Error) {
                        // check if longStackTraceZone is here
                        var trace = Zone.currentTask && Zone.currentTask.data &&
                            Zone.currentTask.data[creationTrace];
                        if (trace) {
                            // only keep the long stack trace into error when in longStackTraceZone
                            ObjectDefineProperty(value, CURRENT_TASK_TRACE_SYMBOL, { configurable: true, enumerable: false, writable: true, value: trace });
                        }
                    }
                    for (var i = 0; i < queue.length;) {
                        scheduleResolveOrReject(promise, queue[i++], queue[i++], queue[i++], queue[i++]);
                    }
                    if (queue.length == 0 && state == REJECTED) {
                        promise[symbolState] = REJECTED_NO_CATCH;
                        var uncaughtPromiseError = value;
                        try {
                            // Here we throws a new Error to print more readable error log
                            // and if the value is not an error, zone.js builds an `Error`
                            // Object here to attach the stack information.
                            throw new Error('Uncaught (in promise): ' + readableObjectToString(value) +
                                (value && value.stack ? '\n' + value.stack : ''));
                        }
                        catch (err) {
                            uncaughtPromiseError = err;
                        }
                        if (isDisableWrappingUncaughtPromiseRejection) {
                            // If disable wrapping uncaught promise reject
                            // use the value instead of wrapping it.
                            uncaughtPromiseError.throwOriginal = true;
                        }
                        uncaughtPromiseError.rejection = value;
                        uncaughtPromiseError.promise = promise;
                        uncaughtPromiseError.zone = Zone.current;
                        uncaughtPromiseError.task = Zone.currentTask;
                        _uncaughtPromiseErrors.push(uncaughtPromiseError);
                        api.scheduleMicroTask(); // to make sure that it is running
                    }
                }
            }
            // Resolving an already resolved promise is a noop.
            return promise;
        }
        var REJECTION_HANDLED_HANDLER = __symbol__('rejectionHandledHandler');
        function clearRejectedNoCatch(promise) {
            if (promise[symbolState] === REJECTED_NO_CATCH) {
                // if the promise is rejected no catch status
                // and queue.length > 0, means there is a error handler
                // here to handle the rejected promise, we should trigger
                // windows.rejectionhandled eventHandler or nodejs rejectionHandled
                // eventHandler
                try {
                    var handler = Zone[REJECTION_HANDLED_HANDLER];
                    if (handler && typeof handler === 'function') {
                        handler.call(this, { rejection: promise[symbolValue], promise: promise });
                    }
                }
                catch (err) {
                }
                promise[symbolState] = REJECTED;
                for (var i = 0; i < _uncaughtPromiseErrors.length; i++) {
                    if (promise === _uncaughtPromiseErrors[i].promise) {
                        _uncaughtPromiseErrors.splice(i, 1);
                    }
                }
            }
        }
        function scheduleResolveOrReject(promise, zone, chainPromise, onFulfilled, onRejected) {
            clearRejectedNoCatch(promise);
            var promiseState = promise[symbolState];
            var delegate = promiseState ?
                (typeof onFulfilled === 'function') ? onFulfilled : forwardResolution :
                (typeof onRejected === 'function') ? onRejected :
                    forwardRejection;
            zone.scheduleMicroTask(source, function () {
                try {
                    var parentPromiseValue = promise[symbolValue];
                    var isFinallyPromise = !!chainPromise && symbolFinally === chainPromise[symbolFinally];
                    if (isFinallyPromise) {
                        // if the promise is generated from finally call, keep parent promise's state and value
                        chainPromise[symbolParentPromiseValue] = parentPromiseValue;
                        chainPromise[symbolParentPromiseState] = promiseState;
                    }
                    // should not pass value to finally callback
                    var value = zone.run(delegate, undefined, isFinallyPromise && delegate !== forwardRejection && delegate !== forwardResolution ?
                        [] :
                        [parentPromiseValue]);
                    resolvePromise(chainPromise, true, value);
                }
                catch (error) {
                    // if error occurs, should always return this error
                    resolvePromise(chainPromise, false, error);
                }
            }, chainPromise);
        }
        var ZONE_AWARE_PROMISE_TO_STRING = 'function ZoneAwarePromise() { [native code] }';
        var noop = function () { };
        var AggregateError = global.AggregateError;
        var ZoneAwarePromise = /** @class */ (function () {
            function ZoneAwarePromise(executor) {
                var promise = this;
                if (!(promise instanceof ZoneAwarePromise)) {
                    throw new Error('Must be an instanceof Promise.');
                }
                promise[symbolState] = UNRESOLVED;
                promise[symbolValue] = []; // queue;
                try {
                    var onceWrapper = once();
                    executor &&
                        executor(onceWrapper(makeResolver(promise, RESOLVED)), onceWrapper(makeResolver(promise, REJECTED)));
                }
                catch (error) {
                    resolvePromise(promise, false, error);
                }
            }
            ZoneAwarePromise.toString = function () {
                return ZONE_AWARE_PROMISE_TO_STRING;
            };
            ZoneAwarePromise.resolve = function (value) {
                return resolvePromise(new this(null), RESOLVED, value);
            };
            ZoneAwarePromise.reject = function (error) {
                return resolvePromise(new this(null), REJECTED, error);
            };
            ZoneAwarePromise.any = function (values) {
                if (!values || typeof values[Symbol.iterator] !== 'function') {
                    return Promise.reject(new AggregateError([], 'All promises were rejected'));
                }
                var promises = [];
                var count = 0;
                try {
                    for (var _i = 0, values_1 = values; _i < values_1.length; _i++) {
                        var v = values_1[_i];
                        count++;
                        promises.push(ZoneAwarePromise.resolve(v));
                    }
                }
                catch (err) {
                    return Promise.reject(new AggregateError([], 'All promises were rejected'));
                }
                if (count === 0) {
                    return Promise.reject(new AggregateError([], 'All promises were rejected'));
                }
                var finished = false;
                var errors = [];
                return new ZoneAwarePromise(function (resolve, reject) {
                    for (var i = 0; i < promises.length; i++) {
                        promises[i].then(function (v) {
                            if (finished) {
                                return;
                            }
                            finished = true;
                            resolve(v);
                        }, function (err) {
                            errors.push(err);
                            count--;
                            if (count === 0) {
                                finished = true;
                                reject(new AggregateError(errors, 'All promises were rejected'));
                            }
                        });
                    }
                });
            };
            ;
            ZoneAwarePromise.race = function (values) {
                var resolve;
                var reject;
                var promise = new this(function (res, rej) {
                    resolve = res;
                    reject = rej;
                });
                function onResolve(value) {
                    resolve(value);
                }
                function onReject(error) {
                    reject(error);
                }
                for (var _i = 0, values_2 = values; _i < values_2.length; _i++) {
                    var value = values_2[_i];
                    if (!isThenable(value)) {
                        value = this.resolve(value);
                    }
                    value.then(onResolve, onReject);
                }
                return promise;
            };
            ZoneAwarePromise.all = function (values) {
                return ZoneAwarePromise.allWithCallback(values);
            };
            ZoneAwarePromise.allSettled = function (values) {
                var P = this && this.prototype instanceof ZoneAwarePromise ? this : ZoneAwarePromise;
                return P.allWithCallback(values, {
                    thenCallback: function (value) { return ({ status: 'fulfilled', value: value }); },
                    errorCallback: function (err) { return ({ status: 'rejected', reason: err }); }
                });
            };
            ZoneAwarePromise.allWithCallback = function (values, callback) {
                var resolve;
                var reject;
                var promise = new this(function (res, rej) {
                    resolve = res;
                    reject = rej;
                });
                // Start at 2 to prevent prematurely resolving if .then is called immediately.
                var unresolvedCount = 2;
                var valueIndex = 0;
                var resolvedValues = [];
                var _loop_3 = function (value) {
                    if (!isThenable(value)) {
                        value = this_1.resolve(value);
                    }
                    var curValueIndex = valueIndex;
                    try {
                        value.then(function (value) {
                            resolvedValues[curValueIndex] = callback ? callback.thenCallback(value) : value;
                            unresolvedCount--;
                            if (unresolvedCount === 0) {
                                resolve(resolvedValues);
                            }
                        }, function (err) {
                            if (!callback) {
                                reject(err);
                            }
                            else {
                                resolvedValues[curValueIndex] = callback.errorCallback(err);
                                unresolvedCount--;
                                if (unresolvedCount === 0) {
                                    resolve(resolvedValues);
                                }
                            }
                        });
                    }
                    catch (thenErr) {
                        reject(thenErr);
                    }
                    unresolvedCount++;
                    valueIndex++;
                };
                var this_1 = this;
                for (var _i = 0, values_3 = values; _i < values_3.length; _i++) {
                    var value = values_3[_i];
                    _loop_3(value);
                }
                // Make the unresolvedCount zero-based again.
                unresolvedCount -= 2;
                if (unresolvedCount === 0) {
                    resolve(resolvedValues);
                }
                return promise;
            };
            Object.defineProperty(ZoneAwarePromise.prototype, Symbol.toStringTag, {
                get: function () {
                    return 'Promise';
                },
                enumerable: false,
                configurable: true
            });
            Object.defineProperty(ZoneAwarePromise.prototype, Symbol.species, {
                get: function () {
                    return ZoneAwarePromise;
                },
                enumerable: false,
                configurable: true
            });
            ZoneAwarePromise.prototype.then = function (onFulfilled, onRejected) {
                var _a;
                // We must read `Symbol.species` safely because `this` may be anything. For instance, `this`
                // may be an object without a prototype (created through `Object.create(null)`); thus
                // `this.constructor` will be undefined. One of the use cases is SystemJS creating
                // prototype-less objects (modules) via `Object.create(null)`. The SystemJS creates an empty
                // object and copies promise properties into that object (within the `getOrCreateLoad`
                // function). The zone.js then checks if the resolved value has the `then` method and invokes
                // it with the `value` context. Otherwise, this will throw an error: `TypeError: Cannot read
                // properties of undefined (reading 'Symbol(Symbol.species)')`.
                var C = (_a = this.constructor) === null || _a === void 0 ? void 0 : _a[Symbol.species];
                if (!C || typeof C !== 'function') {
                    C = this.constructor || ZoneAwarePromise;
                }
                var chainPromise = new C(noop);
                var zone = Zone.current;
                if (this[symbolState] == UNRESOLVED) {
                    this[symbolValue].push(zone, chainPromise, onFulfilled, onRejected);
                }
                else {
                    scheduleResolveOrReject(this, zone, chainPromise, onFulfilled, onRejected);
                }
                return chainPromise;
            };
            ZoneAwarePromise.prototype.catch = function (onRejected) {
                return this.then(null, onRejected);
            };
            ZoneAwarePromise.prototype.finally = function (onFinally) {
                var _a;
                // See comment on the call to `then` about why thee `Symbol.species` is safely accessed.
                var C = (_a = this.constructor) === null || _a === void 0 ? void 0 : _a[Symbol.species];
                if (!C || typeof C !== 'function') {
                    C = ZoneAwarePromise;
                }
                var chainPromise = new C(noop);
                chainPromise[symbolFinally] = symbolFinally;
                var zone = Zone.current;
                if (this[symbolState] == UNRESOLVED) {
                    this[symbolValue].push(zone, chainPromise, onFinally, onFinally);
                }
                else {
                    scheduleResolveOrReject(this, zone, chainPromise, onFinally, onFinally);
                }
                return chainPromise;
            };
            return ZoneAwarePromise;
        }());
        // Protect against aggressive optimizers dropping seemingly unused properties.
        // E.g. Closure Compiler in advanced mode.
        ZoneAwarePromise['resolve'] = ZoneAwarePromise.resolve;
        ZoneAwarePromise['reject'] = ZoneAwarePromise.reject;
        ZoneAwarePromise['race'] = ZoneAwarePromise.race;
        ZoneAwarePromise['all'] = ZoneAwarePromise.all;
        var NativePromise = global[symbolPromise] = global['Promise'];
        global['Promise'] = ZoneAwarePromise;
        var symbolThenPatched = __symbol__('thenPatched');
        function patchThen(Ctor) {
            var proto = Ctor.prototype;
            var prop = ObjectGetOwnPropertyDescriptor(proto, 'then');
            if (prop && (prop.writable === false || !prop.configurable)) {
                // check Ctor.prototype.then propertyDescriptor is writable or not
                // in meteor env, writable is false, we should ignore such case
                return;
            }
            var originalThen = proto.then;
            // Keep a reference to the original method.
            proto[symbolThen] = originalThen;
            Ctor.prototype.then = function (onResolve, onReject) {
                var _this = this;
                var wrapped = new ZoneAwarePromise(function (resolve, reject) {
                    originalThen.call(_this, resolve, reject);
                });
                return wrapped.then(onResolve, onReject);
            };
            Ctor[symbolThenPatched] = true;
        }
        api.patchThen = patchThen;
        function zoneify(fn) {
            return function (self, args) {
                var resultPromise = fn.apply(self, args);
                if (resultPromise instanceof ZoneAwarePromise) {
                    return resultPromise;
                }
                var ctor = resultPromise.constructor;
                if (!ctor[symbolThenPatched]) {
                    patchThen(ctor);
                }
                return resultPromise;
            };
        }
        if (NativePromise) {
            patchThen(NativePromise);
            patchMethod(global, 'fetch', function (delegate) { return zoneify(delegate); });
        }
        // This is not part of public API, but it is useful for tests, so we expose it.
        Promise[Zone.__symbol__('uncaughtPromiseErrors')] = _uncaughtPromiseErrors;
        return ZoneAwarePromise;
    });
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    // override Function.prototype.toString to make zone.js patched function
    // look like native function
    Zone.__load_patch('toString', function (global) {
        // patch Func.prototype.toString to let them look like native
        var originalFunctionToString = Function.prototype.toString;
        var ORIGINAL_DELEGATE_SYMBOL = zoneSymbol$1('OriginalDelegate');
        var PROMISE_SYMBOL = zoneSymbol$1('Promise');
        var ERROR_SYMBOL = zoneSymbol$1('Error');
        var newFunctionToString = function toString() {
            if (typeof this === 'function') {
                var originalDelegate = this[ORIGINAL_DELEGATE_SYMBOL];
                if (originalDelegate) {
                    if (typeof originalDelegate === 'function') {
                        return originalFunctionToString.call(originalDelegate);
                    }
                    else {
                        return Object.prototype.toString.call(originalDelegate);
                    }
                }
                if (this === Promise) {
                    var nativePromise = global[PROMISE_SYMBOL];
                    if (nativePromise) {
                        return originalFunctionToString.call(nativePromise);
                    }
                }
                if (this === Error) {
                    var nativeError = global[ERROR_SYMBOL];
                    if (nativeError) {
                        return originalFunctionToString.call(nativeError);
                    }
                }
            }
            return originalFunctionToString.call(this);
        };
        newFunctionToString[ORIGINAL_DELEGATE_SYMBOL] = originalFunctionToString;
        Function.prototype.toString = newFunctionToString;
        // patch Object.prototype.toString to let them look like native
        var originalObjectToString = Object.prototype.toString;
        var PROMISE_OBJECT_TO_STRING = '[object Promise]';
        Object.prototype.toString = function () {
            if (typeof Promise === 'function' && this instanceof Promise) {
                return PROMISE_OBJECT_TO_STRING;
            }
            return originalObjectToString.call(this);
        };
    });
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    var passiveSupported = false;
    if (typeof window !== 'undefined') {
        try {
            var options = Object.defineProperty({}, 'passive', {
                get: function () {
                    passiveSupported = true;
                }
            });
            // Note: We pass the `options` object as the event handler too. This is not compatible with the
            // signature of `addEventListener` or `removeEventListener` but enables us to remove the handler
            // without an actual handler.
            window.addEventListener('test', options, options);
            window.removeEventListener('test', options, options);
        }
        catch (err) {
            passiveSupported = false;
        }
    }
    // an identifier to tell ZoneTask do not create a new invoke closure
    var OPTIMIZED_ZONE_EVENT_TASK_DATA = {
        useG: true
    };
    var zoneSymbolEventNames = {};
    var globalSources = {};
    var EVENT_NAME_SYMBOL_REGX = new RegExp('^' + ZONE_SYMBOL_PREFIX + '(\\w+)(true|false)$');
    var IMMEDIATE_PROPAGATION_SYMBOL = zoneSymbol$1('propagationStopped');
    function prepareEventNames(eventName, eventNameToString) {
        var falseEventName = (eventNameToString ? eventNameToString(eventName) : eventName) + FALSE_STR;
        var trueEventName = (eventNameToString ? eventNameToString(eventName) : eventName) + TRUE_STR;
        var symbol = ZONE_SYMBOL_PREFIX + falseEventName;
        var symbolCapture = ZONE_SYMBOL_PREFIX + trueEventName;
        zoneSymbolEventNames[eventName] = {};
        zoneSymbolEventNames[eventName][FALSE_STR] = symbol;
        zoneSymbolEventNames[eventName][TRUE_STR] = symbolCapture;
    }
    function patchEventTarget(_global, api, apis, patchOptions) {
        var ADD_EVENT_LISTENER = (patchOptions && patchOptions.add) || ADD_EVENT_LISTENER_STR;
        var REMOVE_EVENT_LISTENER = (patchOptions && patchOptions.rm) || REMOVE_EVENT_LISTENER_STR;
        var LISTENERS_EVENT_LISTENER = (patchOptions && patchOptions.listeners) || 'eventListeners';
        var REMOVE_ALL_LISTENERS_EVENT_LISTENER = (patchOptions && patchOptions.rmAll) || 'removeAllListeners';
        var zoneSymbolAddEventListener = zoneSymbol$1(ADD_EVENT_LISTENER);
        var ADD_EVENT_LISTENER_SOURCE = '.' + ADD_EVENT_LISTENER + ':';
        var PREPEND_EVENT_LISTENER = 'prependListener';
        var PREPEND_EVENT_LISTENER_SOURCE = '.' + PREPEND_EVENT_LISTENER + ':';
        var invokeTask = function (task, target, event) {
            // for better performance, check isRemoved which is set
            // by removeEventListener
            if (task.isRemoved) {
                return;
            }
            var delegate = task.callback;
            if (typeof delegate === 'object' && delegate.handleEvent) {
                // create the bind version of handleEvent when invoke
                task.callback = function (event) { return delegate.handleEvent(event); };
                task.originalDelegate = delegate;
            }
            // invoke static task.invoke
            // need to try/catch error here, otherwise, the error in one event listener
            // will break the executions of the other event listeners. Also error will
            // not remove the event listener when `once` options is true.
            var error;
            try {
                task.invoke(task, target, [event]);
            }
            catch (err) {
                error = err;
            }
            var options = task.options;
            if (options && typeof options === 'object' && options.once) {
                // if options.once is true, after invoke once remove listener here
                // only browser need to do this, nodejs eventEmitter will cal removeListener
                // inside EventEmitter.once
                var delegate_1 = task.originalDelegate ? task.originalDelegate : task.callback;
                target[REMOVE_EVENT_LISTENER].call(target, event.type, delegate_1, options);
            }
            return error;
        };
        function globalCallback(context, event, isCapture) {
            // https://github.com/angular/zone.js/issues/911, in IE, sometimes
            // event will be undefined, so we need to use window.event
            event = event || _global.event;
            if (!event) {
                return;
            }
            // event.target is needed for Samsung TV and SourceBuffer
            // || global is needed https://github.com/angular/zone.js/issues/190
            var target = context || event.target || _global;
            var tasks = target[zoneSymbolEventNames[event.type][isCapture ? TRUE_STR : FALSE_STR]];
            if (tasks) {
                var errors = [];
                // invoke all tasks which attached to current target with given event.type and capture = false
                // for performance concern, if task.length === 1, just invoke
                if (tasks.length === 1) {
                    var err = invokeTask(tasks[0], target, event);
                    err && errors.push(err);
                }
                else {
                    // https://github.com/angular/zone.js/issues/836
                    // copy the tasks array before invoke, to avoid
                    // the callback will remove itself or other listener
                    var copyTasks = tasks.slice();
                    for (var i = 0; i < copyTasks.length; i++) {
                        if (event && event[IMMEDIATE_PROPAGATION_SYMBOL] === true) {
                            break;
                        }
                        var err = invokeTask(copyTasks[i], target, event);
                        err && errors.push(err);
                    }
                }
                // Since there is only one error, we don't need to schedule microTask
                // to throw the error.
                if (errors.length === 1) {
                    throw errors[0];
                }
                else {
                    var _loop_4 = function (i) {
                        var err = errors[i];
                        api.nativeScheduleMicroTask(function () {
                            throw err;
                        });
                    };
                    for (var i = 0; i < errors.length; i++) {
                        _loop_4(i);
                    }
                }
            }
        }
        // global shared zoneAwareCallback to handle all event callback with capture = false
        var globalZoneAwareCallback = function (event) {
            return globalCallback(this, event, false);
        };
        // global shared zoneAwareCallback to handle all event callback with capture = true
        var globalZoneAwareCaptureCallback = function (event) {
            return globalCallback(this, event, true);
        };
        function patchEventTargetMethods(obj, patchOptions) {
            if (!obj) {
                return false;
            }
            var useGlobalCallback = true;
            if (patchOptions && patchOptions.useG !== undefined) {
                useGlobalCallback = patchOptions.useG;
            }
            var validateHandler = patchOptions && patchOptions.vh;
            var checkDuplicate = true;
            if (patchOptions && patchOptions.chkDup !== undefined) {
                checkDuplicate = patchOptions.chkDup;
            }
            var returnTarget = false;
            if (patchOptions && patchOptions.rt !== undefined) {
                returnTarget = patchOptions.rt;
            }
            var proto = obj;
            while (proto && !proto.hasOwnProperty(ADD_EVENT_LISTENER)) {
                proto = ObjectGetPrototypeOf(proto);
            }
            if (!proto && obj[ADD_EVENT_LISTENER]) {
                // somehow we did not find it, but we can see it. This happens on IE for Window properties.
                proto = obj;
            }
            if (!proto) {
                return false;
            }
            if (proto[zoneSymbolAddEventListener]) {
                return false;
            }
            var eventNameToString = patchOptions && patchOptions.eventNameToString;
            // a shared global taskData to pass data for scheduleEventTask
            // so we do not need to create a new object just for pass some data
            var taskData = {};
            var nativeAddEventListener = proto[zoneSymbolAddEventListener] = proto[ADD_EVENT_LISTENER];
            var nativeRemoveEventListener = proto[zoneSymbol$1(REMOVE_EVENT_LISTENER)] =
                proto[REMOVE_EVENT_LISTENER];
            var nativeListeners = proto[zoneSymbol$1(LISTENERS_EVENT_LISTENER)] =
                proto[LISTENERS_EVENT_LISTENER];
            var nativeRemoveAllListeners = proto[zoneSymbol$1(REMOVE_ALL_LISTENERS_EVENT_LISTENER)] =
                proto[REMOVE_ALL_LISTENERS_EVENT_LISTENER];
            var nativePrependEventListener;
            if (patchOptions && patchOptions.prepend) {
                nativePrependEventListener = proto[zoneSymbol$1(patchOptions.prepend)] =
                    proto[patchOptions.prepend];
            }
            /**
             * This util function will build an option object with passive option
             * to handle all possible input from the user.
             */
            function buildEventListenerOptions(options, passive) {
                if (!passiveSupported && typeof options === 'object' && options) {
                    // doesn't support passive but user want to pass an object as options.
                    // this will not work on some old browser, so we just pass a boolean
                    // as useCapture parameter
                    return !!options.capture;
                }
                if (!passiveSupported || !passive) {
                    return options;
                }
                if (typeof options === 'boolean') {
                    return { capture: options, passive: true };
                }
                if (!options) {
                    return { passive: true };
                }
                if (typeof options === 'object' && options.passive !== false) {
                    return Object.assign(Object.assign({}, options), { passive: true });
                }
                return options;
            }
            var customScheduleGlobal = function (task) {
                // if there is already a task for the eventName + capture,
                // just return, because we use the shared globalZoneAwareCallback here.
                if (taskData.isExisting) {
                    return;
                }
                return nativeAddEventListener.call(taskData.target, taskData.eventName, taskData.capture ? globalZoneAwareCaptureCallback : globalZoneAwareCallback, taskData.options);
            };
            var customCancelGlobal = function (task) {
                // if task is not marked as isRemoved, this call is directly
                // from Zone.prototype.cancelTask, we should remove the task
                // from tasksList of target first
                if (!task.isRemoved) {
                    var symbolEventNames = zoneSymbolEventNames[task.eventName];
                    var symbolEventName = void 0;
                    if (symbolEventNames) {
                        symbolEventName = symbolEventNames[task.capture ? TRUE_STR : FALSE_STR];
                    }
                    var existingTasks = symbolEventName && task.target[symbolEventName];
                    if (existingTasks) {
                        for (var i = 0; i < existingTasks.length; i++) {
                            var existingTask = existingTasks[i];
                            if (existingTask === task) {
                                existingTasks.splice(i, 1);
                                // set isRemoved to data for faster invokeTask check
                                task.isRemoved = true;
                                if (existingTasks.length === 0) {
                                    // all tasks for the eventName + capture have gone,
                                    // remove globalZoneAwareCallback and remove the task cache from target
                                    task.allRemoved = true;
                                    task.target[symbolEventName] = null;
                                }
                                break;
                            }
                        }
                    }
                }
                // if all tasks for the eventName + capture have gone,
                // we will really remove the global event callback,
                // if not, return
                if (!task.allRemoved) {
                    return;
                }
                return nativeRemoveEventListener.call(task.target, task.eventName, task.capture ? globalZoneAwareCaptureCallback : globalZoneAwareCallback, task.options);
            };
            var customScheduleNonGlobal = function (task) {
                return nativeAddEventListener.call(taskData.target, taskData.eventName, task.invoke, taskData.options);
            };
            var customSchedulePrepend = function (task) {
                return nativePrependEventListener.call(taskData.target, taskData.eventName, task.invoke, taskData.options);
            };
            var customCancelNonGlobal = function (task) {
                return nativeRemoveEventListener.call(task.target, task.eventName, task.invoke, task.options);
            };
            var customSchedule = useGlobalCallback ? customScheduleGlobal : customScheduleNonGlobal;
            var customCancel = useGlobalCallback ? customCancelGlobal : customCancelNonGlobal;
            var compareTaskCallbackVsDelegate = function (task, delegate) {
                var typeOfDelegate = typeof delegate;
                return (typeOfDelegate === 'function' && task.callback === delegate) ||
                    (typeOfDelegate === 'object' && task.originalDelegate === delegate);
            };
            var compare = (patchOptions && patchOptions.diff) ? patchOptions.diff : compareTaskCallbackVsDelegate;
            var unpatchedEvents = Zone[zoneSymbol$1('UNPATCHED_EVENTS')];
            var passiveEvents = _global[zoneSymbol$1('PASSIVE_EVENTS')];
            var makeAddListener = function (nativeListener, addSource, customScheduleFn, customCancelFn, returnTarget, prepend) {
                if (returnTarget === void 0) { returnTarget = false; }
                if (prepend === void 0) { prepend = false; }
                return function () {
                    var target = this || _global;
                    var eventName = arguments[0];
                    if (patchOptions && patchOptions.transferEventName) {
                        eventName = patchOptions.transferEventName(eventName);
                    }
                    var delegate = arguments[1];
                    if (!delegate) {
                        return nativeListener.apply(this, arguments);
                    }
                    if (isNode && eventName === 'uncaughtException') {
                        // don't patch uncaughtException of nodejs to prevent endless loop
                        return nativeListener.apply(this, arguments);
                    }
                    // don't create the bind delegate function for handleEvent
                    // case here to improve addEventListener performance
                    // we will create the bind delegate when invoke
                    var isHandleEvent = false;
                    if (typeof delegate !== 'function') {
                        if (!delegate.handleEvent) {
                            return nativeListener.apply(this, arguments);
                        }
                        isHandleEvent = true;
                    }
                    if (validateHandler && !validateHandler(nativeListener, delegate, target, arguments)) {
                        return;
                    }
                    var passive = passiveSupported && !!passiveEvents && passiveEvents.indexOf(eventName) !== -1;
                    var options = buildEventListenerOptions(arguments[2], passive);
                    if (unpatchedEvents) {
                        // check unpatched list
                        for (var i = 0; i < unpatchedEvents.length; i++) {
                            if (eventName === unpatchedEvents[i]) {
                                if (passive) {
                                    return nativeListener.call(target, eventName, delegate, options);
                                }
                                else {
                                    return nativeListener.apply(this, arguments);
                                }
                            }
                        }
                    }
                    var capture = !options ? false : typeof options === 'boolean' ? true : options.capture;
                    var once = options && typeof options === 'object' ? options.once : false;
                    var zone = Zone.current;
                    var symbolEventNames = zoneSymbolEventNames[eventName];
                    if (!symbolEventNames) {
                        prepareEventNames(eventName, eventNameToString);
                        symbolEventNames = zoneSymbolEventNames[eventName];
                    }
                    var symbolEventName = symbolEventNames[capture ? TRUE_STR : FALSE_STR];
                    var existingTasks = target[symbolEventName];
                    var isExisting = false;
                    if (existingTasks) {
                        // already have task registered
                        isExisting = true;
                        if (checkDuplicate) {
                            for (var i = 0; i < existingTasks.length; i++) {
                                if (compare(existingTasks[i], delegate)) {
                                    // same callback, same capture, same event name, just return
                                    return;
                                }
                            }
                        }
                    }
                    else {
                        existingTasks = target[symbolEventName] = [];
                    }
                    var source;
                    var constructorName = target.constructor['name'];
                    var targetSource = globalSources[constructorName];
                    if (targetSource) {
                        source = targetSource[eventName];
                    }
                    if (!source) {
                        source = constructorName + addSource +
                            (eventNameToString ? eventNameToString(eventName) : eventName);
                    }
                    // do not create a new object as task.data to pass those things
                    // just use the global shared one
                    taskData.options = options;
                    if (once) {
                        // if addEventListener with once options, we don't pass it to
                        // native addEventListener, instead we keep the once setting
                        // and handle ourselves.
                        taskData.options.once = false;
                    }
                    taskData.target = target;
                    taskData.capture = capture;
                    taskData.eventName = eventName;
                    taskData.isExisting = isExisting;
                    var data = useGlobalCallback ? OPTIMIZED_ZONE_EVENT_TASK_DATA : undefined;
                    // keep taskData into data to allow onScheduleEventTask to access the task information
                    if (data) {
                        data.taskData = taskData;
                    }
                    var task = zone.scheduleEventTask(source, delegate, data, customScheduleFn, customCancelFn);
                    // should clear taskData.target to avoid memory leak
                    // issue, https://github.com/angular/angular/issues/20442
                    taskData.target = null;
                    // need to clear up taskData because it is a global object
                    if (data) {
                        data.taskData = null;
                    }
                    // have to save those information to task in case
                    // application may call task.zone.cancelTask() directly
                    if (once) {
                        options.once = true;
                    }
                    if (!(!passiveSupported && typeof task.options === 'boolean')) {
                        // if not support passive, and we pass an option object
                        // to addEventListener, we should save the options to task
                        task.options = options;
                    }
                    task.target = target;
                    task.capture = capture;
                    task.eventName = eventName;
                    if (isHandleEvent) {
                        // save original delegate for compare to check duplicate
                        task.originalDelegate = delegate;
                    }
                    if (!prepend) {
                        existingTasks.push(task);
                    }
                    else {
                        existingTasks.unshift(task);
                    }
                    if (returnTarget) {
                        return target;
                    }
                };
            };
            proto[ADD_EVENT_LISTENER] = makeAddListener(nativeAddEventListener, ADD_EVENT_LISTENER_SOURCE, customSchedule, customCancel, returnTarget);
            if (nativePrependEventListener) {
                proto[PREPEND_EVENT_LISTENER] = makeAddListener(nativePrependEventListener, PREPEND_EVENT_LISTENER_SOURCE, customSchedulePrepend, customCancel, returnTarget, true);
            }
            proto[REMOVE_EVENT_LISTENER] = function () {
                var target = this || _global;
                var eventName = arguments[0];
                if (patchOptions && patchOptions.transferEventName) {
                    eventName = patchOptions.transferEventName(eventName);
                }
                var options = arguments[2];
                var capture = !options ? false : typeof options === 'boolean' ? true : options.capture;
                var delegate = arguments[1];
                if (!delegate) {
                    return nativeRemoveEventListener.apply(this, arguments);
                }
                if (validateHandler &&
                    !validateHandler(nativeRemoveEventListener, delegate, target, arguments)) {
                    return;
                }
                var symbolEventNames = zoneSymbolEventNames[eventName];
                var symbolEventName;
                if (symbolEventNames) {
                    symbolEventName = symbolEventNames[capture ? TRUE_STR : FALSE_STR];
                }
                var existingTasks = symbolEventName && target[symbolEventName];
                if (existingTasks) {
                    for (var i = 0; i < existingTasks.length; i++) {
                        var existingTask = existingTasks[i];
                        if (compare(existingTask, delegate)) {
                            existingTasks.splice(i, 1);
                            // set isRemoved to data for faster invokeTask check
                            existingTask.isRemoved = true;
                            if (existingTasks.length === 0) {
                                // all tasks for the eventName + capture have gone,
                                // remove globalZoneAwareCallback and remove the task cache from target
                                existingTask.allRemoved = true;
                                target[symbolEventName] = null;
                                // in the target, we have an event listener which is added by on_property
                                // such as target.onclick = function() {}, so we need to clear this internal
                                // property too if all delegates all removed
                                if (typeof eventName === 'string') {
                                    var onPropertySymbol = ZONE_SYMBOL_PREFIX + 'ON_PROPERTY' + eventName;
                                    target[onPropertySymbol] = null;
                                }
                            }
                            existingTask.zone.cancelTask(existingTask);
                            if (returnTarget) {
                                return target;
                            }
                            return;
                        }
                    }
                }
                // issue 930, didn't find the event name or callback
                // from zone kept existingTasks, the callback maybe
                // added outside of zone, we need to call native removeEventListener
                // to try to remove it.
                return nativeRemoveEventListener.apply(this, arguments);
            };
            proto[LISTENERS_EVENT_LISTENER] = function () {
                var target = this || _global;
                var eventName = arguments[0];
                if (patchOptions && patchOptions.transferEventName) {
                    eventName = patchOptions.transferEventName(eventName);
                }
                var listeners = [];
                var tasks = findEventTasks(target, eventNameToString ? eventNameToString(eventName) : eventName);
                for (var i = 0; i < tasks.length; i++) {
                    var task = tasks[i];
                    var delegate = task.originalDelegate ? task.originalDelegate : task.callback;
                    listeners.push(delegate);
                }
                return listeners;
            };
            proto[REMOVE_ALL_LISTENERS_EVENT_LISTENER] = function () {
                var target = this || _global;
                var eventName = arguments[0];
                if (!eventName) {
                    var keys = Object.keys(target);
                    for (var i = 0; i < keys.length; i++) {
                        var prop = keys[i];
                        var match = EVENT_NAME_SYMBOL_REGX.exec(prop);
                        var evtName = match && match[1];
                        // in nodejs EventEmitter, removeListener event is
                        // used for monitoring the removeListener call,
                        // so just keep removeListener eventListener until
                        // all other eventListeners are removed
                        if (evtName && evtName !== 'removeListener') {
                            this[REMOVE_ALL_LISTENERS_EVENT_LISTENER].call(this, evtName);
                        }
                    }
                    // remove removeListener listener finally
                    this[REMOVE_ALL_LISTENERS_EVENT_LISTENER].call(this, 'removeListener');
                }
                else {
                    if (patchOptions && patchOptions.transferEventName) {
                        eventName = patchOptions.transferEventName(eventName);
                    }
                    var symbolEventNames = zoneSymbolEventNames[eventName];
                    if (symbolEventNames) {
                        var symbolEventName = symbolEventNames[FALSE_STR];
                        var symbolCaptureEventName = symbolEventNames[TRUE_STR];
                        var tasks = target[symbolEventName];
                        var captureTasks = target[symbolCaptureEventName];
                        if (tasks) {
                            var removeTasks = tasks.slice();
                            for (var i = 0; i < removeTasks.length; i++) {
                                var task = removeTasks[i];
                                var delegate = task.originalDelegate ? task.originalDelegate : task.callback;
                                this[REMOVE_EVENT_LISTENER].call(this, eventName, delegate, task.options);
                            }
                        }
                        if (captureTasks) {
                            var removeTasks = captureTasks.slice();
                            for (var i = 0; i < removeTasks.length; i++) {
                                var task = removeTasks[i];
                                var delegate = task.originalDelegate ? task.originalDelegate : task.callback;
                                this[REMOVE_EVENT_LISTENER].call(this, eventName, delegate, task.options);
                            }
                        }
                    }
                }
                if (returnTarget) {
                    return this;
                }
            };
            // for native toString patch
            attachOriginToPatched(proto[ADD_EVENT_LISTENER], nativeAddEventListener);
            attachOriginToPatched(proto[REMOVE_EVENT_LISTENER], nativeRemoveEventListener);
            if (nativeRemoveAllListeners) {
                attachOriginToPatched(proto[REMOVE_ALL_LISTENERS_EVENT_LISTENER], nativeRemoveAllListeners);
            }
            if (nativeListeners) {
                attachOriginToPatched(proto[LISTENERS_EVENT_LISTENER], nativeListeners);
            }
            return true;
        }
        var results = [];
        for (var i = 0; i < apis.length; i++) {
            results[i] = patchEventTargetMethods(apis[i], patchOptions);
        }
        return results;
    }
    function findEventTasks(target, eventName) {
        if (!eventName) {
            var foundTasks = [];
            for (var prop in target) {
                var match = EVENT_NAME_SYMBOL_REGX.exec(prop);
                var evtName = match && match[1];
                if (evtName && (!eventName || evtName === eventName)) {
                    var tasks = target[prop];
                    if (tasks) {
                        for (var i = 0; i < tasks.length; i++) {
                            foundTasks.push(tasks[i]);
                        }
                    }
                }
            }
            return foundTasks;
        }
        var symbolEventName = zoneSymbolEventNames[eventName];
        if (!symbolEventName) {
            prepareEventNames(eventName);
            symbolEventName = zoneSymbolEventNames[eventName];
        }
        var captureFalseTasks = target[symbolEventName[FALSE_STR]];
        var captureTrueTasks = target[symbolEventName[TRUE_STR]];
        if (!captureFalseTasks) {
            return captureTrueTasks ? captureTrueTasks.slice() : [];
        }
        else {
            return captureTrueTasks ? captureFalseTasks.concat(captureTrueTasks) :
                captureFalseTasks.slice();
        }
    }
    function patchEventPrototype(global, api) {
        var Event = global['Event'];
        if (Event && Event.prototype) {
            api.patchMethod(Event.prototype, 'stopImmediatePropagation', function (delegate) { return function (self, args) {
                self[IMMEDIATE_PROPAGATION_SYMBOL] = true;
                // we need to call the native stopImmediatePropagation
                // in case in some hybrid application, some part of
                // application will be controlled by zone, some are not
                delegate && delegate.apply(self, args);
            }; });
        }
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function patchCallbacks(api, target, targetName, method, callbacks) {
        var symbol = Zone.__symbol__(method);
        if (target[symbol]) {
            return;
        }
        var nativeDelegate = target[symbol] = target[method];
        target[method] = function (name, opts, options) {
            if (opts && opts.prototype) {
                callbacks.forEach(function (callback) {
                    var source = "".concat(targetName, ".").concat(method, "::") + callback;
                    var prototype = opts.prototype;
                    // Note: the `patchCallbacks` is used for patching the `document.registerElement` and
                    // `customElements.define`. We explicitly wrap the patching code into try-catch since
                    // callbacks may be already patched by other web components frameworks (e.g. LWC), and they
                    // make those properties non-writable. This means that patching callback will throw an error
                    // `cannot assign to read-only property`. See this code as an example:
                    // https://github.com/salesforce/lwc/blob/master/packages/@lwc/engine-core/src/framework/base-bridge-element.ts#L180-L186
                    // We don't want to stop the application rendering if we couldn't patch some
                    // callback, e.g. `attributeChangedCallback`.
                    try {
                        if (prototype.hasOwnProperty(callback)) {
                            var descriptor = api.ObjectGetOwnPropertyDescriptor(prototype, callback);
                            if (descriptor && descriptor.value) {
                                descriptor.value = api.wrapWithCurrentZone(descriptor.value, source);
                                api._redefineProperty(opts.prototype, callback, descriptor);
                            }
                            else if (prototype[callback]) {
                                prototype[callback] = api.wrapWithCurrentZone(prototype[callback], source);
                            }
                        }
                        else if (prototype[callback]) {
                            prototype[callback] = api.wrapWithCurrentZone(prototype[callback], source);
                        }
                    }
                    catch (_a) {
                        // Note: we leave the catch block empty since there's no way to handle the error related
                        // to non-writable property.
                    }
                });
            }
            return nativeDelegate.call(target, name, opts, options);
        };
        api.attachOriginToPatched(target[method], nativeDelegate);
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function filterProperties(target, onProperties, ignoreProperties) {
        if (!ignoreProperties || ignoreProperties.length === 0) {
            return onProperties;
        }
        var tip = ignoreProperties.filter(function (ip) { return ip.target === target; });
        if (!tip || tip.length === 0) {
            return onProperties;
        }
        var targetIgnoreProperties = tip[0].ignoreProperties;
        return onProperties.filter(function (op) { return targetIgnoreProperties.indexOf(op) === -1; });
    }
    function patchFilteredProperties(target, onProperties, ignoreProperties, prototype) {
        // check whether target is available, sometimes target will be undefined
        // because different browser or some 3rd party plugin.
        if (!target) {
            return;
        }
        var filteredProperties = filterProperties(target, onProperties, ignoreProperties);
        patchOnProperties(target, filteredProperties, prototype);
    }
    /**
     * Get all event name properties which the event name startsWith `on`
     * from the target object itself, inherited properties are not considered.
     */
    function getOnEventNames(target) {
        return Object.getOwnPropertyNames(target)
            .filter(function (name) { return name.startsWith('on') && name.length > 2; })
            .map(function (name) { return name.substring(2); });
    }
    function propertyDescriptorPatch(api, _global) {
        if (isNode && !isMix) {
            return;
        }
        if (Zone[api.symbol('patchEvents')]) {
            // events are already been patched by legacy patch.
            return;
        }
        var ignoreProperties = _global['__Zone_ignore_on_properties'];
        // for browsers that we can patch the descriptor:  Chrome & Firefox
        var patchTargets = [];
        if (isBrowser) {
            var internalWindow_1 = window;
            patchTargets = patchTargets.concat([
                'Document', 'SVGElement', 'Element', 'HTMLElement', 'HTMLBodyElement', 'HTMLMediaElement',
                'HTMLFrameSetElement', 'HTMLFrameElement', 'HTMLIFrameElement', 'HTMLMarqueeElement', 'Worker'
            ]);
            var ignoreErrorProperties = isIE() ? [{ target: internalWindow_1, ignoreProperties: ['error'] }] : [];
            // in IE/Edge, onProp not exist in window object, but in WindowPrototype
            // so we need to pass WindowPrototype to check onProp exist or not
            patchFilteredProperties(internalWindow_1, getOnEventNames(internalWindow_1), ignoreProperties ? ignoreProperties.concat(ignoreErrorProperties) : ignoreProperties, ObjectGetPrototypeOf(internalWindow_1));
        }
        patchTargets = patchTargets.concat([
            'XMLHttpRequest', 'XMLHttpRequestEventTarget', 'IDBIndex', 'IDBRequest', 'IDBOpenDBRequest',
            'IDBDatabase', 'IDBTransaction', 'IDBCursor', 'WebSocket'
        ]);
        for (var i = 0; i < patchTargets.length; i++) {
            var target = _global[patchTargets[i]];
            target && target.prototype &&
                patchFilteredProperties(target.prototype, getOnEventNames(target.prototype), ignoreProperties);
        }
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    Zone.__load_patch('util', function (global, Zone, api) {
        // Collect native event names by looking at properties
        // on the global namespace, e.g. 'onclick'.
        var eventNames = getOnEventNames(global);
        api.patchOnProperties = patchOnProperties;
        api.patchMethod = patchMethod;
        api.bindArguments = bindArguments;
        api.patchMacroTask = patchMacroTask;
        // In earlier version of zone.js (<0.9.0), we use env name `__zone_symbol__BLACK_LISTED_EVENTS` to
        // define which events will not be patched by `Zone.js`.
        // In newer version (>=0.9.0), we change the env name to `__zone_symbol__UNPATCHED_EVENTS` to keep
        // the name consistent with angular repo.
        // The  `__zone_symbol__BLACK_LISTED_EVENTS` is deprecated, but it is still be supported for
        // backwards compatibility.
        var SYMBOL_BLACK_LISTED_EVENTS = Zone.__symbol__('BLACK_LISTED_EVENTS');
        var SYMBOL_UNPATCHED_EVENTS = Zone.__symbol__('UNPATCHED_EVENTS');
        if (global[SYMBOL_UNPATCHED_EVENTS]) {
            global[SYMBOL_BLACK_LISTED_EVENTS] = global[SYMBOL_UNPATCHED_EVENTS];
        }
        if (global[SYMBOL_BLACK_LISTED_EVENTS]) {
            Zone[SYMBOL_BLACK_LISTED_EVENTS] = Zone[SYMBOL_UNPATCHED_EVENTS] =
                global[SYMBOL_BLACK_LISTED_EVENTS];
        }
        api.patchEventPrototype = patchEventPrototype;
        api.patchEventTarget = patchEventTarget;
        api.isIEOrEdge = isIEOrEdge;
        api.ObjectDefineProperty = ObjectDefineProperty;
        api.ObjectGetOwnPropertyDescriptor = ObjectGetOwnPropertyDescriptor;
        api.ObjectCreate = ObjectCreate;
        api.ArraySlice = ArraySlice;
        api.patchClass = patchClass;
        api.wrapWithCurrentZone = wrapWithCurrentZone;
        api.filterProperties = filterProperties;
        api.attachOriginToPatched = attachOriginToPatched;
        api._redefineProperty = Object.defineProperty;
        api.patchCallbacks = patchCallbacks;
        api.getGlobalObjects = function () { return ({
            globalSources: globalSources,
            zoneSymbolEventNames: zoneSymbolEventNames,
            eventNames: eventNames,
            isBrowser: isBrowser,
            isMix: isMix,
            isNode: isNode,
            TRUE_STR: TRUE_STR,
            FALSE_STR: FALSE_STR,
            ZONE_SYMBOL_PREFIX: ZONE_SYMBOL_PREFIX,
            ADD_EVENT_LISTENER_STR: ADD_EVENT_LISTENER_STR,
            REMOVE_EVENT_LISTENER_STR: REMOVE_EVENT_LISTENER_STR
        }); };
    });
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    /*
     * This is necessary for Chrome and Chrome mobile, to enable
     * things like redefining `createdCallback` on an element.
     */
    var zoneSymbol;
    var _defineProperty;
    var _getOwnPropertyDescriptor;
    var _create;
    var unconfigurablesKey;
    function propertyPatch() {
        zoneSymbol = Zone.__symbol__;
        _defineProperty = Object[zoneSymbol('defineProperty')] = Object.defineProperty;
        _getOwnPropertyDescriptor = Object[zoneSymbol('getOwnPropertyDescriptor')] =
            Object.getOwnPropertyDescriptor;
        _create = Object.create;
        unconfigurablesKey = zoneSymbol('unconfigurables');
        Object.defineProperty = function (obj, prop, desc) {
            if (isUnconfigurable(obj, prop)) {
                throw new TypeError('Cannot assign to read only property \'' + prop + '\' of ' + obj);
            }
            var originalConfigurableFlag = desc.configurable;
            if (prop !== 'prototype') {
                desc = rewriteDescriptor(obj, prop, desc);
            }
            return _tryDefineProperty(obj, prop, desc, originalConfigurableFlag);
        };
        Object.defineProperties = function (obj, props) {
            Object.keys(props).forEach(function (prop) {
                Object.defineProperty(obj, prop, props[prop]);
            });
            for (var _i = 0, _b = Object.getOwnPropertySymbols(props); _i < _b.length; _i++) {
                var sym = _b[_i];
                var desc = Object.getOwnPropertyDescriptor(props, sym);
                // Since `Object.getOwnPropertySymbols` returns *all* symbols,
                // including non-enumerable ones, retrieve property descriptor and check
                // enumerability there. Proceed with the rewrite only when a property is
                // enumerable to make the logic consistent with the way regular
                // properties are retrieved (via `Object.keys`, which respects
                // `enumerable: false` flag). More information:
                // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Enumerability_and_ownership_of_properties#retrieval
                if (desc === null || desc === void 0 ? void 0 : desc.enumerable) {
                    Object.defineProperty(obj, sym, props[sym]);
                }
            }
            return obj;
        };
        Object.create = function (proto, propertiesObject) {
            if (typeof propertiesObject === 'object' && !Object.isFrozen(propertiesObject)) {
                Object.keys(propertiesObject).forEach(function (prop) {
                    propertiesObject[prop] = rewriteDescriptor(proto, prop, propertiesObject[prop]);
                });
            }
            return _create(proto, propertiesObject);
        };
        Object.getOwnPropertyDescriptor = function (obj, prop) {
            var desc = _getOwnPropertyDescriptor(obj, prop);
            if (desc && isUnconfigurable(obj, prop)) {
                desc.configurable = false;
            }
            return desc;
        };
    }
    function _redefineProperty(obj, prop, desc) {
        var originalConfigurableFlag = desc.configurable;
        desc = rewriteDescriptor(obj, prop, desc);
        return _tryDefineProperty(obj, prop, desc, originalConfigurableFlag);
    }
    function isUnconfigurable(obj, prop) {
        return obj && obj[unconfigurablesKey] && obj[unconfigurablesKey][prop];
    }
    function rewriteDescriptor(obj, prop, desc) {
        // issue-927, if the desc is frozen, don't try to change the desc
        if (!Object.isFrozen(desc)) {
            desc.configurable = true;
        }
        if (!desc.configurable) {
            // issue-927, if the obj is frozen, don't try to set the desc to obj
            if (!obj[unconfigurablesKey] && !Object.isFrozen(obj)) {
                _defineProperty(obj, unconfigurablesKey, { writable: true, value: {} });
            }
            if (obj[unconfigurablesKey]) {
                obj[unconfigurablesKey][prop] = true;
            }
        }
        return desc;
    }
    function _tryDefineProperty(obj, prop, desc, originalConfigurableFlag) {
        try {
            return _defineProperty(obj, prop, desc);
        }
        catch (error) {
            if (desc.configurable) {
                // In case of errors, when the configurable flag was likely set by rewriteDescriptor(),
                // let's retry with the original flag value
                if (typeof originalConfigurableFlag == 'undefined') {
                    delete desc.configurable;
                }
                else {
                    desc.configurable = originalConfigurableFlag;
                }
                try {
                    return _defineProperty(obj, prop, desc);
                }
                catch (error) {
                    var swallowError = false;
                    if (prop === 'createdCallback' || prop === 'attachedCallback' ||
                        prop === 'detachedCallback' || prop === 'attributeChangedCallback') {
                        // We only swallow the error in registerElement patch
                        // this is the work around since some applications
                        // fail if we throw the error
                        swallowError = true;
                    }
                    if (!swallowError) {
                        throw error;
                    }
                    // TODO: @JiaLiPassion, Some application such as `registerElement` patch
                    // still need to swallow the error, in the future after these applications
                    // are updated, the following logic can be removed.
                    var descJson = null;
                    try {
                        descJson = JSON.stringify(desc);
                    }
                    catch (error) {
                        descJson = desc.toString();
                    }
                    console.log("Attempting to configure '".concat(prop, "' with descriptor '").concat(descJson, "' on object '").concat(obj, "' and got error, giving up: ").concat(error));
                }
            }
            else {
                throw error;
            }
        }
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function eventTargetLegacyPatch(_global, api) {
        var _b = api.getGlobalObjects(), eventNames = _b.eventNames, globalSources = _b.globalSources, zoneSymbolEventNames = _b.zoneSymbolEventNames, TRUE_STR = _b.TRUE_STR, FALSE_STR = _b.FALSE_STR, ZONE_SYMBOL_PREFIX = _b.ZONE_SYMBOL_PREFIX;
        var WTF_ISSUE_555 = 'Anchor,Area,Audio,BR,Base,BaseFont,Body,Button,Canvas,Content,DList,Directory,Div,Embed,FieldSet,Font,Form,Frame,FrameSet,HR,Head,Heading,Html,IFrame,Image,Input,Keygen,LI,Label,Legend,Link,Map,Marquee,Media,Menu,Meta,Meter,Mod,OList,Object,OptGroup,Option,Output,Paragraph,Pre,Progress,Quote,Script,Select,Source,Span,Style,TableCaption,TableCell,TableCol,Table,TableRow,TableSection,TextArea,Title,Track,UList,Unknown,Video';
        var NO_EVENT_TARGET = 'ApplicationCache,EventSource,FileReader,InputMethodContext,MediaController,MessagePort,Node,Performance,SVGElementInstance,SharedWorker,TextTrack,TextTrackCue,TextTrackList,WebKitNamedFlow,Window,Worker,WorkerGlobalScope,XMLHttpRequest,XMLHttpRequestEventTarget,XMLHttpRequestUpload,IDBRequest,IDBOpenDBRequest,IDBDatabase,IDBTransaction,IDBCursor,DBIndex,WebSocket'
            .split(',');
        var EVENT_TARGET = 'EventTarget';
        var apis = [];
        var isWtf = _global['wtf'];
        var WTF_ISSUE_555_ARRAY = WTF_ISSUE_555.split(',');
        if (isWtf) {
            // Workaround for: https://github.com/google/tracing-framework/issues/555
            apis = WTF_ISSUE_555_ARRAY.map(function (v) { return 'HTML' + v + 'Element'; }).concat(NO_EVENT_TARGET);
        }
        else if (_global[EVENT_TARGET]) {
            apis.push(EVENT_TARGET);
        }
        else {
            // Note: EventTarget is not available in all browsers,
            // if it's not available, we instead patch the APIs in the IDL that inherit from EventTarget
            apis = NO_EVENT_TARGET;
        }
        var isDisableIECheck = _global['__Zone_disable_IE_check'] || false;
        var isEnableCrossContextCheck = _global['__Zone_enable_cross_context_check'] || false;
        var ieOrEdge = api.isIEOrEdge();
        var ADD_EVENT_LISTENER_SOURCE = '.addEventListener:';
        var FUNCTION_WRAPPER = '[object FunctionWrapper]';
        var BROWSER_TOOLS = 'function __BROWSERTOOLS_CONSOLE_SAFEFUNC() { [native code] }';
        var pointerEventsMap = {
            'MSPointerCancel': 'pointercancel',
            'MSPointerDown': 'pointerdown',
            'MSPointerEnter': 'pointerenter',
            'MSPointerHover': 'pointerhover',
            'MSPointerLeave': 'pointerleave',
            'MSPointerMove': 'pointermove',
            'MSPointerOut': 'pointerout',
            'MSPointerOver': 'pointerover',
            'MSPointerUp': 'pointerup'
        };
        //  predefine all __zone_symbol__ + eventName + true/false string
        for (var i = 0; i < eventNames.length; i++) {
            var eventName = eventNames[i];
            var falseEventName = eventName + FALSE_STR;
            var trueEventName = eventName + TRUE_STR;
            var symbol = ZONE_SYMBOL_PREFIX + falseEventName;
            var symbolCapture = ZONE_SYMBOL_PREFIX + trueEventName;
            zoneSymbolEventNames[eventName] = {};
            zoneSymbolEventNames[eventName][FALSE_STR] = symbol;
            zoneSymbolEventNames[eventName][TRUE_STR] = symbolCapture;
        }
        //  predefine all task.source string
        for (var i = 0; i < WTF_ISSUE_555_ARRAY.length; i++) {
            var target = WTF_ISSUE_555_ARRAY[i];
            var targets = globalSources[target] = {};
            for (var j = 0; j < eventNames.length; j++) {
                var eventName = eventNames[j];
                targets[eventName] = target + ADD_EVENT_LISTENER_SOURCE + eventName;
            }
        }
        var checkIEAndCrossContext = function (nativeDelegate, delegate, target, args) {
            if (!isDisableIECheck && ieOrEdge) {
                if (isEnableCrossContextCheck) {
                    try {
                        var testString = delegate.toString();
                        if ((testString === FUNCTION_WRAPPER || testString == BROWSER_TOOLS)) {
                            nativeDelegate.apply(target, args);
                            return false;
                        }
                    }
                    catch (error) {
                        nativeDelegate.apply(target, args);
                        return false;
                    }
                }
                else {
                    var testString = delegate.toString();
                    if ((testString === FUNCTION_WRAPPER || testString == BROWSER_TOOLS)) {
                        nativeDelegate.apply(target, args);
                        return false;
                    }
                }
            }
            else if (isEnableCrossContextCheck) {
                try {
                    delegate.toString();
                }
                catch (error) {
                    nativeDelegate.apply(target, args);
                    return false;
                }
            }
            return true;
        };
        var apiTypes = [];
        for (var i = 0; i < apis.length; i++) {
            var type = _global[apis[i]];
            apiTypes.push(type && type.prototype);
        }
        // vh is validateHandler to check event handler
        // is valid or not(for security check)
        api.patchEventTarget(_global, api, apiTypes, {
            vh: checkIEAndCrossContext,
            transferEventName: function (eventName) {
                var pointerEventName = pointerEventsMap[eventName];
                return pointerEventName || eventName;
            }
        });
        Zone[api.symbol('patchEventTarget')] = !!_global[EVENT_TARGET];
        return true;
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    // we have to patch the instance since the proto is non-configurable
    function apply(api, _global) {
        var _b = api.getGlobalObjects(), ADD_EVENT_LISTENER_STR = _b.ADD_EVENT_LISTENER_STR, REMOVE_EVENT_LISTENER_STR = _b.REMOVE_EVENT_LISTENER_STR;
        var WS = _global.WebSocket;
        // On Safari window.EventTarget doesn't exist so need to patch WS add/removeEventListener
        // On older Chrome, no need since EventTarget was already patched
        if (!_global.EventTarget) {
            api.patchEventTarget(_global, api, [WS.prototype]);
        }
        _global.WebSocket = function (x, y) {
            var socket = arguments.length > 1 ? new WS(x, y) : new WS(x);
            var proxySocket;
            var proxySocketProto;
            // Safari 7.0 has non-configurable own 'onmessage' and friends properties on the socket instance
            var onmessageDesc = api.ObjectGetOwnPropertyDescriptor(socket, 'onmessage');
            if (onmessageDesc && onmessageDesc.configurable === false) {
                proxySocket = api.ObjectCreate(socket);
                // socket have own property descriptor 'onopen', 'onmessage', 'onclose', 'onerror'
                // but proxySocket not, so we will keep socket as prototype and pass it to
                // patchOnProperties method
                proxySocketProto = socket;
                [ADD_EVENT_LISTENER_STR, REMOVE_EVENT_LISTENER_STR, 'send', 'close'].forEach(function (propName) {
                    proxySocket[propName] = function () {
                        var args = api.ArraySlice.call(arguments);
                        if (propName === ADD_EVENT_LISTENER_STR || propName === REMOVE_EVENT_LISTENER_STR) {
                            var eventName = args.length > 0 ? args[0] : undefined;
                            if (eventName) {
                                var propertySymbol = Zone.__symbol__('ON_PROPERTY' + eventName);
                                socket[propertySymbol] = proxySocket[propertySymbol];
                            }
                        }
                        return socket[propName].apply(socket, args);
                    };
                });
            }
            else {
                // we can patch the real socket
                proxySocket = socket;
            }
            api.patchOnProperties(proxySocket, ['close', 'error', 'message', 'open'], proxySocketProto);
            return proxySocket;
        };
        var globalWebSocket = _global['WebSocket'];
        for (var prop in WS) {
            globalWebSocket[prop] = WS[prop];
        }
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function propertyDescriptorLegacyPatch(api, _global) {
        var _b = api.getGlobalObjects(), isNode = _b.isNode, isMix = _b.isMix;
        if (isNode && !isMix) {
            return;
        }
        if (!canPatchViaPropertyDescriptor(api, _global)) {
            var supportsWebSocket = typeof WebSocket !== 'undefined';
            // Safari, Android browsers (Jelly Bean)
            patchViaCapturingAllTheEvents(api);
            api.patchClass('XMLHttpRequest');
            if (supportsWebSocket) {
                apply(api, _global);
            }
            Zone[api.symbol('patchEvents')] = true;
        }
    }
    function canPatchViaPropertyDescriptor(api, _global) {
        var _b = api.getGlobalObjects(), isBrowser = _b.isBrowser, isMix = _b.isMix;
        if ((isBrowser || isMix) &&
            !api.ObjectGetOwnPropertyDescriptor(HTMLElement.prototype, 'onclick') &&
            typeof Element !== 'undefined') {
            // WebKit https://bugs.webkit.org/show_bug.cgi?id=134364
            // IDL interface attributes are not configurable
            var desc = api.ObjectGetOwnPropertyDescriptor(Element.prototype, 'onclick');
            if (desc && !desc.configurable)
                return false;
            // try to use onclick to detect whether we can patch via propertyDescriptor
            // because XMLHttpRequest is not available in service worker
            if (desc) {
                api.ObjectDefineProperty(Element.prototype, 'onclick', {
                    enumerable: true,
                    configurable: true,
                    get: function () {
                        return true;
                    }
                });
                var div = document.createElement('div');
                var result = !!div.onclick;
                api.ObjectDefineProperty(Element.prototype, 'onclick', desc);
                return result;
            }
        }
        var XMLHttpRequest = _global['XMLHttpRequest'];
        if (!XMLHttpRequest) {
            // XMLHttpRequest is not available in service worker
            return false;
        }
        var ON_READY_STATE_CHANGE = 'onreadystatechange';
        var XMLHttpRequestPrototype = XMLHttpRequest.prototype;
        var xhrDesc = api.ObjectGetOwnPropertyDescriptor(XMLHttpRequestPrototype, ON_READY_STATE_CHANGE);
        // add enumerable and configurable here because in opera
        // by default XMLHttpRequest.prototype.onreadystatechange is undefined
        // without adding enumerable and configurable will cause onreadystatechange
        // non-configurable
        // and if XMLHttpRequest.prototype.onreadystatechange is undefined,
        // we should set a real desc instead a fake one
        if (xhrDesc) {
            api.ObjectDefineProperty(XMLHttpRequestPrototype, ON_READY_STATE_CHANGE, {
                enumerable: true,
                configurable: true,
                get: function () {
                    return true;
                }
            });
            var req = new XMLHttpRequest();
            var result = !!req.onreadystatechange;
            // restore original desc
            api.ObjectDefineProperty(XMLHttpRequestPrototype, ON_READY_STATE_CHANGE, xhrDesc || {});
            return result;
        }
        else {
            var SYMBOL_FAKE_ONREADYSTATECHANGE_1 = api.symbol('fake');
            api.ObjectDefineProperty(XMLHttpRequestPrototype, ON_READY_STATE_CHANGE, {
                enumerable: true,
                configurable: true,
                get: function () {
                    return this[SYMBOL_FAKE_ONREADYSTATECHANGE_1];
                },
                set: function (value) {
                    this[SYMBOL_FAKE_ONREADYSTATECHANGE_1] = value;
                }
            });
            var req = new XMLHttpRequest();
            var detectFunc = function () { };
            req.onreadystatechange = detectFunc;
            var result = req[SYMBOL_FAKE_ONREADYSTATECHANGE_1] === detectFunc;
            req.onreadystatechange = null;
            return result;
        }
    }
    var globalEventHandlersEventNames = [
        'abort',
        'animationcancel',
        'animationend',
        'animationiteration',
        'auxclick',
        'beforeinput',
        'blur',
        'cancel',
        'canplay',
        'canplaythrough',
        'change',
        'compositionstart',
        'compositionupdate',
        'compositionend',
        'cuechange',
        'click',
        'close',
        'contextmenu',
        'curechange',
        'dblclick',
        'drag',
        'dragend',
        'dragenter',
        'dragexit',
        'dragleave',
        'dragover',
        'drop',
        'durationchange',
        'emptied',
        'ended',
        'error',
        'focus',
        'focusin',
        'focusout',
        'gotpointercapture',
        'input',
        'invalid',
        'keydown',
        'keypress',
        'keyup',
        'load',
        'loadstart',
        'loadeddata',
        'loadedmetadata',
        'lostpointercapture',
        'mousedown',
        'mouseenter',
        'mouseleave',
        'mousemove',
        'mouseout',
        'mouseover',
        'mouseup',
        'mousewheel',
        'orientationchange',
        'pause',
        'play',
        'playing',
        'pointercancel',
        'pointerdown',
        'pointerenter',
        'pointerleave',
        'pointerlockchange',
        'mozpointerlockchange',
        'webkitpointerlockerchange',
        'pointerlockerror',
        'mozpointerlockerror',
        'webkitpointerlockerror',
        'pointermove',
        'pointout',
        'pointerover',
        'pointerup',
        'progress',
        'ratechange',
        'reset',
        'resize',
        'scroll',
        'seeked',
        'seeking',
        'select',
        'selectionchange',
        'selectstart',
        'show',
        'sort',
        'stalled',
        'submit',
        'suspend',
        'timeupdate',
        'volumechange',
        'touchcancel',
        'touchmove',
        'touchstart',
        'touchend',
        'transitioncancel',
        'transitionend',
        'waiting',
        'wheel'
    ];
    var documentEventNames = [
        'afterscriptexecute', 'beforescriptexecute', 'DOMContentLoaded', 'freeze', 'fullscreenchange',
        'mozfullscreenchange', 'webkitfullscreenchange', 'msfullscreenchange', 'fullscreenerror',
        'mozfullscreenerror', 'webkitfullscreenerror', 'msfullscreenerror', 'readystatechange',
        'visibilitychange', 'resume'
    ];
    var windowEventNames = [
        'absolutedeviceorientation',
        'afterinput',
        'afterprint',
        'appinstalled',
        'beforeinstallprompt',
        'beforeprint',
        'beforeunload',
        'devicelight',
        'devicemotion',
        'deviceorientation',
        'deviceorientationabsolute',
        'deviceproximity',
        'hashchange',
        'languagechange',
        'message',
        'mozbeforepaint',
        'offline',
        'online',
        'paint',
        'pageshow',
        'pagehide',
        'popstate',
        'rejectionhandled',
        'storage',
        'unhandledrejection',
        'unload',
        'userproximity',
        'vrdisplayconnected',
        'vrdisplaydisconnected',
        'vrdisplaypresentchange'
    ];
    var htmlElementEventNames = [
        'beforecopy', 'beforecut', 'beforepaste', 'copy', 'cut', 'paste', 'dragstart', 'loadend',
        'animationstart', 'search', 'transitionrun', 'transitionstart', 'webkitanimationend',
        'webkitanimationiteration', 'webkitanimationstart', 'webkittransitionend'
    ];
    var ieElementEventNames = [
        'activate',
        'afterupdate',
        'ariarequest',
        'beforeactivate',
        'beforedeactivate',
        'beforeeditfocus',
        'beforeupdate',
        'cellchange',
        'controlselect',
        'dataavailable',
        'datasetchanged',
        'datasetcomplete',
        'errorupdate',
        'filterchange',
        'layoutcomplete',
        'losecapture',
        'move',
        'moveend',
        'movestart',
        'propertychange',
        'resizeend',
        'resizestart',
        'rowenter',
        'rowexit',
        'rowsdelete',
        'rowsinserted',
        'command',
        'compassneedscalibration',
        'deactivate',
        'help',
        'mscontentzoom',
        'msmanipulationstatechanged',
        'msgesturechange',
        'msgesturedoubletap',
        'msgestureend',
        'msgesturehold',
        'msgesturestart',
        'msgesturetap',
        'msgotpointercapture',
        'msinertiastart',
        'mslostpointercapture',
        'mspointercancel',
        'mspointerdown',
        'mspointerenter',
        'mspointerhover',
        'mspointerleave',
        'mspointermove',
        'mspointerout',
        'mspointerover',
        'mspointerup',
        'pointerout',
        'mssitemodejumplistitemremoved',
        'msthumbnailclick',
        'stop',
        'storagecommit'
    ];
    var webglEventNames = ['webglcontextrestored', 'webglcontextlost', 'webglcontextcreationerror'];
    var formEventNames = ['autocomplete', 'autocompleteerror'];
    var detailEventNames = ['toggle'];
    var eventNames = __spreadArray(__spreadArray(__spreadArray(__spreadArray(__spreadArray(__spreadArray(__spreadArray(__spreadArray([], globalEventHandlersEventNames, true), webglEventNames, true), formEventNames, true), detailEventNames, true), documentEventNames, true), windowEventNames, true), htmlElementEventNames, true), ieElementEventNames, true);
    // Whenever any eventListener fires, we check the eventListener target and all parents
    // for `onwhatever` properties and replace them with zone-bound functions
    // - Chrome (for now)
    function patchViaCapturingAllTheEvents(api) {
        var unboundKey = api.symbol('unbound');
        var _loop_5 = function (i) {
            var property = eventNames[i];
            var onproperty = 'on' + property;
            self.addEventListener(property, function (event) {
                var elt = event.target, bound, source;
                if (elt) {
                    source = elt.constructor['name'] + '.' + onproperty;
                }
                else {
                    source = 'unknown.' + onproperty;
                }
                while (elt) {
                    if (elt[onproperty] && !elt[onproperty][unboundKey]) {
                        bound = api.wrapWithCurrentZone(elt[onproperty], source);
                        bound[unboundKey] = elt[onproperty];
                        elt[onproperty] = bound;
                    }
                    elt = elt.parentElement;
                }
            }, true);
        };
        for (var i = 0; i < eventNames.length; i++) {
            _loop_5(i);
        }
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function registerElementPatch(_global, api) {
        var _b = api.getGlobalObjects(), isBrowser = _b.isBrowser, isMix = _b.isMix;
        if ((!isBrowser && !isMix) || !('registerElement' in _global.document)) {
            return;
        }
        var callbacks = ['createdCallback', 'attachedCallback', 'detachedCallback', 'attributeChangedCallback'];
        api.patchCallbacks(api, document, 'Document', 'registerElement', callbacks);
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    (function (_global) {
        var symbolPrefix = _global['__Zone_symbol_prefix'] || '__zone_symbol__';
        function __symbol__(name) {
            return symbolPrefix + name;
        }
        _global[__symbol__('legacyPatch')] = function () {
            var Zone = _global['Zone'];
            Zone.__load_patch('defineProperty', function (global, Zone, api) {
                api._redefineProperty = _redefineProperty;
                propertyPatch();
            });
            Zone.__load_patch('registerElement', function (global, Zone, api) {
                registerElementPatch(global, api);
            });
            Zone.__load_patch('EventTargetLegacy', function (global, Zone, api) {
                eventTargetLegacyPatch(global, api);
                propertyDescriptorLegacyPatch(api, global);
            });
        };
    })(typeof window !== 'undefined' ?
        window :
        typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {});
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    var taskSymbol = zoneSymbol$1('zoneTask');
    function patchTimer(window, setName, cancelName, nameSuffix) {
        var setNative = null;
        var clearNative = null;
        setName += nameSuffix;
        cancelName += nameSuffix;
        var tasksByHandleId = {};
        function scheduleTask(task) {
            var data = task.data;
            data.args[0] = function () {
                return task.invoke.apply(this, arguments);
            };
            data.handleId = setNative.apply(window, data.args);
            return task;
        }
        function clearTask(task) {
            return clearNative.call(window, task.data.handleId);
        }
        setNative =
            patchMethod(window, setName, function (delegate) { return function (self, args) {
                if (typeof args[0] === 'function') {
                    var options_1 = {
                        isPeriodic: nameSuffix === 'Interval',
                        delay: (nameSuffix === 'Timeout' || nameSuffix === 'Interval') ? args[1] || 0 :
                            undefined,
                        args: args
                    };
                    var callback_1 = args[0];
                    args[0] = function timer() {
                        try {
                            return callback_1.apply(this, arguments);
                        }
                        finally {
                            // issue-934, task will be cancelled
                            // even it is a periodic task such as
                            // setInterval
                            // https://github.com/angular/angular/issues/40387
                            // Cleanup tasksByHandleId should be handled before scheduleTask
                            // Since some zoneSpec may intercept and doesn't trigger
                            // scheduleFn(scheduleTask) provided here.
                            if (!(options_1.isPeriodic)) {
                                if (typeof options_1.handleId === 'number') {
                                    // in non-nodejs env, we remove timerId
                                    // from local cache
                                    delete tasksByHandleId[options_1.handleId];
                                }
                                else if (options_1.handleId) {
                                    // Node returns complex objects as handleIds
                                    // we remove task reference from timer object
                                    options_1.handleId[taskSymbol] = null;
                                }
                            }
                        }
                    };
                    var task = scheduleMacroTaskWithCurrentZone(setName, args[0], options_1, scheduleTask, clearTask);
                    if (!task) {
                        return task;
                    }
                    // Node.js must additionally support the ref and unref functions.
                    var handle = task.data.handleId;
                    if (typeof handle === 'number') {
                        // for non nodejs env, we save handleId: task
                        // mapping in local cache for clearTimeout
                        tasksByHandleId[handle] = task;
                    }
                    else if (handle) {
                        // for nodejs env, we save task
                        // reference in timerId Object for clearTimeout
                        handle[taskSymbol] = task;
                    }
                    // check whether handle is null, because some polyfill or browser
                    // may return undefined from setTimeout/setInterval/setImmediate/requestAnimationFrame
                    if (handle && handle.ref && handle.unref && typeof handle.ref === 'function' &&
                        typeof handle.unref === 'function') {
                        task.ref = handle.ref.bind(handle);
                        task.unref = handle.unref.bind(handle);
                    }
                    if (typeof handle === 'number' || handle) {
                        return handle;
                    }
                    return task;
                }
                else {
                    // cause an error by calling it directly.
                    return delegate.apply(window, args);
                }
            }; });
        clearNative =
            patchMethod(window, cancelName, function (delegate) { return function (self, args) {
                var id = args[0];
                var task;
                if (typeof id === 'number') {
                    // non nodejs env.
                    task = tasksByHandleId[id];
                }
                else {
                    // nodejs env.
                    task = id && id[taskSymbol];
                    // other environments.
                    if (!task) {
                        task = id;
                    }
                }
                if (task && typeof task.type === 'string') {
                    if (task.state !== 'notScheduled' &&
                        (task.cancelFn && task.data.isPeriodic || task.runCount === 0)) {
                        if (typeof id === 'number') {
                            delete tasksByHandleId[id];
                        }
                        else if (id) {
                            id[taskSymbol] = null;
                        }
                        // Do not cancel already canceled functions
                        task.zone.cancelTask(task);
                    }
                }
                else {
                    // cause an error by calling it directly.
                    delegate.apply(window, args);
                }
            }; });
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function patchCustomElements(_global, api) {
        var _b = api.getGlobalObjects(), isBrowser = _b.isBrowser, isMix = _b.isMix;
        if ((!isBrowser && !isMix) || !_global['customElements'] || !('customElements' in _global)) {
            return;
        }
        var callbacks = ['connectedCallback', 'disconnectedCallback', 'adoptedCallback', 'attributeChangedCallback'];
        api.patchCallbacks(api, _global.customElements, 'customElements', 'define', callbacks);
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    function eventTargetPatch(_global, api) {
        if (Zone[api.symbol('patchEventTarget')]) {
            // EventTarget is already patched.
            return;
        }
        var _b = api.getGlobalObjects(), eventNames = _b.eventNames, zoneSymbolEventNames = _b.zoneSymbolEventNames, TRUE_STR = _b.TRUE_STR, FALSE_STR = _b.FALSE_STR, ZONE_SYMBOL_PREFIX = _b.ZONE_SYMBOL_PREFIX;
        //  predefine all __zone_symbol__ + eventName + true/false string
        for (var i = 0; i < eventNames.length; i++) {
            var eventName = eventNames[i];
            var falseEventName = eventName + FALSE_STR;
            var trueEventName = eventName + TRUE_STR;
            var symbol = ZONE_SYMBOL_PREFIX + falseEventName;
            var symbolCapture = ZONE_SYMBOL_PREFIX + trueEventName;
            zoneSymbolEventNames[eventName] = {};
            zoneSymbolEventNames[eventName][FALSE_STR] = symbol;
            zoneSymbolEventNames[eventName][TRUE_STR] = symbolCapture;
        }
        var EVENT_TARGET = _global['EventTarget'];
        if (!EVENT_TARGET || !EVENT_TARGET.prototype) {
            return;
        }
        api.patchEventTarget(_global, api, [EVENT_TARGET && EVENT_TARGET.prototype]);
        return true;
    }
    function patchEvent(global, api) {
        api.patchEventPrototype(global, api);
    }
    /**
     * @license
     * Copyright Google LLC All Rights Reserved.
     *
     * Use of this source code is governed by an MIT-style license that can be
     * found in the LICENSE file at https://angular.io/license
     */
    Zone.__load_patch('legacy', function (global) {
        var legacyPatch = global[Zone.__symbol__('legacyPatch')];
        if (legacyPatch) {
            legacyPatch();
        }
    });
    Zone.__load_patch('queueMicrotask', function (global, Zone, api) {
        api.patchMethod(global, 'queueMicrotask', function (delegate) {
            return function (self, args) {
                Zone.current.scheduleMicroTask('queueMicrotask', args[0]);
            };
        });
    });
    Zone.__load_patch('timers', function (global) {
        var set = 'set';
        var clear = 'clear';
        patchTimer(global, set, clear, 'Timeout');
        patchTimer(global, set, clear, 'Interval');
        patchTimer(global, set, clear, 'Immediate');
    });
    Zone.__load_patch('requestAnimationFrame', function (global) {
        patchTimer(global, 'request', 'cancel', 'AnimationFrame');
        patchTimer(global, 'mozRequest', 'mozCancel', 'AnimationFrame');
        patchTimer(global, 'webkitRequest', 'webkitCancel', 'AnimationFrame');
    });
    Zone.__load_patch('blocking', function (global, Zone) {
        var blockingMethods = ['alert', 'prompt', 'confirm'];
        for (var i = 0; i < blockingMethods.length; i++) {
            var name_2 = blockingMethods[i];
            patchMethod(global, name_2, function (delegate, symbol, name) {
                return function (s, args) {
                    return Zone.current.run(delegate, global, args, name);
                };
            });
        }
    });
    Zone.__load_patch('EventTarget', function (global, Zone, api) {
        patchEvent(global, api);
        eventTargetPatch(global, api);
        // patch XMLHttpRequestEventTarget's addEventListener/removeEventListener
        var XMLHttpRequestEventTarget = global['XMLHttpRequestEventTarget'];
        if (XMLHttpRequestEventTarget && XMLHttpRequestEventTarget.prototype) {
            api.patchEventTarget(global, api, [XMLHttpRequestEventTarget.prototype]);
        }
    });
    Zone.__load_patch('MutationObserver', function (global, Zone, api) {
        patchClass('MutationObserver');
        patchClass('WebKitMutationObserver');
    });
    Zone.__load_patch('IntersectionObserver', function (global, Zone, api) {
        patchClass('IntersectionObserver');
    });
    Zone.__load_patch('FileReader', function (global, Zone, api) {
        patchClass('FileReader');
    });
    Zone.__load_patch('on_property', function (global, Zone, api) {
        propertyDescriptorPatch(api, global);
    });
    Zone.__load_patch('customElements', function (global, Zone, api) {
        patchCustomElements(global, api);
    });
    Zone.__load_patch('XHR', function (global, Zone) {
        // Treat XMLHttpRequest as a macrotask.
        patchXHR(global);
        var XHR_TASK = zoneSymbol$1('xhrTask');
        var XHR_SYNC = zoneSymbol$1('xhrSync');
        var XHR_LISTENER = zoneSymbol$1('xhrListener');
        var XHR_SCHEDULED = zoneSymbol$1('xhrScheduled');
        var XHR_URL = zoneSymbol$1('xhrURL');
        var XHR_ERROR_BEFORE_SCHEDULED = zoneSymbol$1('xhrErrorBeforeScheduled');
        function patchXHR(window) {
            var XMLHttpRequest = window['XMLHttpRequest'];
            if (!XMLHttpRequest) {
                // XMLHttpRequest is not available in service worker
                return;
            }
            var XMLHttpRequestPrototype = XMLHttpRequest.prototype;
            function findPendingTask(target) {
                return target[XHR_TASK];
            }
            var oriAddListener = XMLHttpRequestPrototype[ZONE_SYMBOL_ADD_EVENT_LISTENER];
            var oriRemoveListener = XMLHttpRequestPrototype[ZONE_SYMBOL_REMOVE_EVENT_LISTENER];
            if (!oriAddListener) {
                var XMLHttpRequestEventTarget_1 = window['XMLHttpRequestEventTarget'];
                if (XMLHttpRequestEventTarget_1) {
                    var XMLHttpRequestEventTargetPrototype = XMLHttpRequestEventTarget_1.prototype;
                    oriAddListener = XMLHttpRequestEventTargetPrototype[ZONE_SYMBOL_ADD_EVENT_LISTENER];
                    oriRemoveListener = XMLHttpRequestEventTargetPrototype[ZONE_SYMBOL_REMOVE_EVENT_LISTENER];
                }
            }
            var READY_STATE_CHANGE = 'readystatechange';
            var SCHEDULED = 'scheduled';
            function scheduleTask(task) {
                var data = task.data;
                var target = data.target;
                target[XHR_SCHEDULED] = false;
                target[XHR_ERROR_BEFORE_SCHEDULED] = false;
                // remove existing event listener
                var listener = target[XHR_LISTENER];
                if (!oriAddListener) {
                    oriAddListener = target[ZONE_SYMBOL_ADD_EVENT_LISTENER];
                    oriRemoveListener = target[ZONE_SYMBOL_REMOVE_EVENT_LISTENER];
                }
                if (listener) {
                    oriRemoveListener.call(target, READY_STATE_CHANGE, listener);
                }
                var newListener = target[XHR_LISTENER] = function () {
                    if (target.readyState === target.DONE) {
                        // sometimes on some browsers XMLHttpRequest will fire onreadystatechange with
                        // readyState=4 multiple times, so we need to check task state here
                        if (!data.aborted && target[XHR_SCHEDULED] && task.state === SCHEDULED) {
                            // check whether the xhr has registered onload listener
                            // if that is the case, the task should invoke after all
                            // onload listeners finish.
                            // Also if the request failed without response (status = 0), the load event handler
                            // will not be triggered, in that case, we should also invoke the placeholder callback
                            // to close the XMLHttpRequest::send macroTask.
                            // https://github.com/angular/angular/issues/38795
                            var loadTasks = target[Zone.__symbol__('loadfalse')];
                            if (target.status !== 0 && loadTasks && loadTasks.length > 0) {
                                var oriInvoke_1 = task.invoke;
                                task.invoke = function () {
                                    // need to load the tasks again, because in other
                                    // load listener, they may remove themselves
                                    var loadTasks = target[Zone.__symbol__('loadfalse')];
                                    for (var i = 0; i < loadTasks.length; i++) {
                                        if (loadTasks[i] === task) {
                                            loadTasks.splice(i, 1);
                                        }
                                    }
                                    if (!data.aborted && task.state === SCHEDULED) {
                                        oriInvoke_1.call(task);
                                    }
                                };
                                loadTasks.push(task);
                            }
                            else {
                                task.invoke();
                            }
                        }
                        else if (!data.aborted && target[XHR_SCHEDULED] === false) {
                            // error occurs when xhr.send()
                            target[XHR_ERROR_BEFORE_SCHEDULED] = true;
                        }
                    }
                };
                oriAddListener.call(target, READY_STATE_CHANGE, newListener);
                var storedTask = target[XHR_TASK];
                if (!storedTask) {
                    target[XHR_TASK] = task;
                }
                sendNative.apply(target, data.args);
                target[XHR_SCHEDULED] = true;
                return task;
            }
            function placeholderCallback() { }
            function clearTask(task) {
                var data = task.data;
                // Note - ideally, we would call data.target.removeEventListener here, but it's too late
                // to prevent it from firing. So instead, we store info for the event listener.
                data.aborted = true;
                return abortNative.apply(data.target, data.args);
            }
            var openNative = patchMethod(XMLHttpRequestPrototype, 'open', function () { return function (self, args) {
                self[XHR_SYNC] = args[2] == false;
                self[XHR_URL] = args[1];
                return openNative.apply(self, args);
            }; });
            var XMLHTTPREQUEST_SOURCE = 'XMLHttpRequest.send';
            var fetchTaskAborting = zoneSymbol$1('fetchTaskAborting');
            var fetchTaskScheduling = zoneSymbol$1('fetchTaskScheduling');
            var sendNative = patchMethod(XMLHttpRequestPrototype, 'send', function () { return function (self, args) {
                if (Zone.current[fetchTaskScheduling] === true) {
                    // a fetch is scheduling, so we are using xhr to polyfill fetch
                    // and because we already schedule macroTask for fetch, we should
                    // not schedule a macroTask for xhr again
                    return sendNative.apply(self, args);
                }
                if (self[XHR_SYNC]) {
                    // if the XHR is sync there is no task to schedule, just execute the code.
                    return sendNative.apply(self, args);
                }
                else {
                    var options = { target: self, url: self[XHR_URL], isPeriodic: false, args: args, aborted: false };
                    var task = scheduleMacroTaskWithCurrentZone(XMLHTTPREQUEST_SOURCE, placeholderCallback, options, scheduleTask, clearTask);
                    if (self && self[XHR_ERROR_BEFORE_SCHEDULED] === true && !options.aborted &&
                        task.state === SCHEDULED) {
                        // xhr request throw error when send
                        // we should invoke task instead of leaving a scheduled
                        // pending macroTask
                        task.invoke();
                    }
                }
            }; });
            var abortNative = patchMethod(XMLHttpRequestPrototype, 'abort', function () { return function (self, args) {
                var task = findPendingTask(self);
                if (task && typeof task.type == 'string') {
                    // If the XHR has already completed, do nothing.
                    // If the XHR has already been aborted, do nothing.
                    // Fix #569, call abort multiple times before done will cause
                    // macroTask task count be negative number
                    if (task.cancelFn == null || (task.data && task.data.aborted)) {
                        return;
                    }
                    task.zone.cancelTask(task);
                }
                else if (Zone.current[fetchTaskAborting] === true) {
                    // the abort is called from fetch polyfill, we need to call native abort of XHR.
                    return abortNative.apply(self, args);
                }
                // Otherwise, we are trying to abort an XHR which has not yet been sent, so there is no
                // task
                // to cancel. Do nothing.
            }; });
        }
    });
    Zone.__load_patch('geolocation', function (global) {
        /// GEO_LOCATION
        if (global['navigator'] && global['navigator'].geolocation) {
            patchPrototype(global['navigator'].geolocation, ['getCurrentPosition', 'watchPosition']);
        }
    });
    Zone.__load_patch('PromiseRejectionEvent', function (global, Zone) {
        // handle unhandled promise rejection
        function findPromiseRejectionHandler(evtName) {
            return function (e) {
                var eventTasks = findEventTasks(global, evtName);
                eventTasks.forEach(function (eventTask) {
                    // windows has added unhandledrejection event listener
                    // trigger the event listener
                    var PromiseRejectionEvent = global['PromiseRejectionEvent'];
                    if (PromiseRejectionEvent) {
                        var evt = new PromiseRejectionEvent(evtName, { promise: e.promise, reason: e.rejection });
                        eventTask.invoke(evt);
                    }
                });
            };
        }
        if (global['PromiseRejectionEvent']) {
            Zone[zoneSymbol$1('unhandledPromiseRejectionHandler')] =
                findPromiseRejectionHandler('unhandledrejection');
            Zone[zoneSymbol$1('rejectionHandledHandler')] =
                findPromiseRejectionHandler('rejectionhandled');
        }
    });
}));


/***/ }),

/***/ 79366:
/*!******************************************************!*\
  !*** ./node_modules/core-js/internals/a-callable.js ***!
  \******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var tryToString = __webpack_require__(/*! ../internals/try-to-string */ 67366);

var TypeError = global.TypeError;

// `Assert: IsCallable(argument) is true`
module.exports = function (argument) {
  if (isCallable(argument)) return argument;
  throw TypeError(tryToString(argument) + ' is not a function');
};


/***/ }),

/***/ 8254:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/a-possible-prototype.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);

var String = global.String;
var TypeError = global.TypeError;

module.exports = function (argument) {
  if (typeof argument == 'object' || isCallable(argument)) return argument;
  throw TypeError("Can't set " + String(argument) + ' as a prototype');
};


/***/ }),

/***/ 58734:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/an-instance.js ***!
  \*******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isPrototypeOf = __webpack_require__(/*! ../internals/object-is-prototype-of */ 70846);

var TypeError = global.TypeError;

module.exports = function (it, Prototype) {
  if (isPrototypeOf(Prototype, it)) return it;
  throw TypeError('Incorrect invocation');
};


/***/ }),

/***/ 16583:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/an-object.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);

var String = global.String;
var TypeError = global.TypeError;

// `Assert: Type(argument) is Object`
module.exports = function (argument) {
  if (isObject(argument)) return argument;
  throw TypeError(String(argument) + ' is not an object');
};


/***/ }),

/***/ 73588:
/*!***********************************************************************!*\
  !*** ./node_modules/core-js/internals/array-buffer-non-extensible.js ***!
  \***********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

// FF26- bug: ArrayBuffers are non-extensible, but Object.isExtensible does not report it
var fails = __webpack_require__(/*! ../internals/fails */ 27944);

module.exports = fails(function () {
  if (typeof ArrayBuffer == 'function') {
    var buffer = new ArrayBuffer(8);
    // eslint-disable-next-line es/no-object-isextensible, es/no-object-defineproperty -- safe
    if (Object.isExtensible(buffer)) Object.defineProperty(buffer, 'a', { value: 8 });
  }
});


/***/ }),

/***/ 34273:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/array-includes.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var toIndexedObject = __webpack_require__(/*! ../internals/to-indexed-object */ 88436);
var toAbsoluteIndex = __webpack_require__(/*! ../internals/to-absolute-index */ 8064);
var lengthOfArrayLike = __webpack_require__(/*! ../internals/length-of-array-like */ 77951);

// `Array.prototype.{ indexOf, includes }` methods implementation
var createMethod = function (IS_INCLUDES) {
  return function ($this, el, fromIndex) {
    var O = toIndexedObject($this);
    var length = lengthOfArrayLike(O);
    var index = toAbsoluteIndex(fromIndex, length);
    var value;
    // Array#includes uses SameValueZero equality algorithm
    // eslint-disable-next-line no-self-compare -- NaN check
    if (IS_INCLUDES && el != el) while (length > index) {
      value = O[index++];
      // eslint-disable-next-line no-self-compare -- NaN check
      if (value != value) return true;
    // Array#indexOf ignores holes, Array#includes - not
    } else for (;length > index; index++) {
      if ((IS_INCLUDES || index in O) && O[index] === el) return IS_INCLUDES || index || 0;
    } return !IS_INCLUDES && -1;
  };
};

module.exports = {
  // `Array.prototype.includes` method
  // https://tc39.es/ecma262/#sec-array.prototype.includes
  includes: createMethod(true),
  // `Array.prototype.indexOf` method
  // https://tc39.es/ecma262/#sec-array.prototype.indexof
  indexOf: createMethod(false)
};


/***/ }),

/***/ 98657:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/array-iteration.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var bind = __webpack_require__(/*! ../internals/function-bind-context */ 90352);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var IndexedObject = __webpack_require__(/*! ../internals/indexed-object */ 32462);
var toObject = __webpack_require__(/*! ../internals/to-object */ 73784);
var lengthOfArrayLike = __webpack_require__(/*! ../internals/length-of-array-like */ 77951);
var arraySpeciesCreate = __webpack_require__(/*! ../internals/array-species-create */ 14275);

var push = uncurryThis([].push);

// `Array.prototype.{ forEach, map, filter, some, every, find, findIndex, filterReject }` methods implementation
var createMethod = function (TYPE) {
  var IS_MAP = TYPE == 1;
  var IS_FILTER = TYPE == 2;
  var IS_SOME = TYPE == 3;
  var IS_EVERY = TYPE == 4;
  var IS_FIND_INDEX = TYPE == 6;
  var IS_FILTER_REJECT = TYPE == 7;
  var NO_HOLES = TYPE == 5 || IS_FIND_INDEX;
  return function ($this, callbackfn, that, specificCreate) {
    var O = toObject($this);
    var self = IndexedObject(O);
    var boundFunction = bind(callbackfn, that);
    var length = lengthOfArrayLike(self);
    var index = 0;
    var create = specificCreate || arraySpeciesCreate;
    var target = IS_MAP ? create($this, length) : IS_FILTER || IS_FILTER_REJECT ? create($this, 0) : undefined;
    var value, result;
    for (;length > index; index++) if (NO_HOLES || index in self) {
      value = self[index];
      result = boundFunction(value, index, O);
      if (TYPE) {
        if (IS_MAP) target[index] = result; // map
        else if (result) switch (TYPE) {
          case 3: return true;              // some
          case 5: return value;             // find
          case 6: return index;             // findIndex
          case 2: push(target, value);      // filter
        } else switch (TYPE) {
          case 4: return false;             // every
          case 7: push(target, value);      // filterReject
        }
      }
    }
    return IS_FIND_INDEX ? -1 : IS_SOME || IS_EVERY ? IS_EVERY : target;
  };
};

module.exports = {
  // `Array.prototype.forEach` method
  // https://tc39.es/ecma262/#sec-array.prototype.foreach
  forEach: createMethod(0),
  // `Array.prototype.map` method
  // https://tc39.es/ecma262/#sec-array.prototype.map
  map: createMethod(1),
  // `Array.prototype.filter` method
  // https://tc39.es/ecma262/#sec-array.prototype.filter
  filter: createMethod(2),
  // `Array.prototype.some` method
  // https://tc39.es/ecma262/#sec-array.prototype.some
  some: createMethod(3),
  // `Array.prototype.every` method
  // https://tc39.es/ecma262/#sec-array.prototype.every
  every: createMethod(4),
  // `Array.prototype.find` method
  // https://tc39.es/ecma262/#sec-array.prototype.find
  find: createMethod(5),
  // `Array.prototype.findIndex` method
  // https://tc39.es/ecma262/#sec-array.prototype.findIndex
  findIndex: createMethod(6),
  // `Array.prototype.filterReject` method
  // https://github.com/tc39/proposal-array-filtering
  filterReject: createMethod(7)
};


/***/ }),

/***/ 45005:
/*!**************************************************************!*\
  !*** ./node_modules/core-js/internals/array-slice-simple.js ***!
  \**************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var toAbsoluteIndex = __webpack_require__(/*! ../internals/to-absolute-index */ 8064);
var lengthOfArrayLike = __webpack_require__(/*! ../internals/length-of-array-like */ 77951);
var createProperty = __webpack_require__(/*! ../internals/create-property */ 67182);

var Array = global.Array;
var max = Math.max;

module.exports = function (O, start, end) {
  var length = lengthOfArrayLike(O);
  var k = toAbsoluteIndex(start, length);
  var fin = toAbsoluteIndex(end === undefined ? length : end, length);
  var result = Array(max(fin - k, 0));
  for (var n = 0; k < fin; k++, n++) createProperty(result, n, O[k]);
  result.length = n;
  return result;
};


/***/ }),

/***/ 66511:
/*!*********************************************************************!*\
  !*** ./node_modules/core-js/internals/array-species-constructor.js ***!
  \*********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isArray = __webpack_require__(/*! ../internals/is-array */ 48143);
var isConstructor = __webpack_require__(/*! ../internals/is-constructor */ 45200);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var SPECIES = wellKnownSymbol('species');
var Array = global.Array;

// a part of `ArraySpeciesCreate` abstract operation
// https://tc39.es/ecma262/#sec-arrayspeciescreate
module.exports = function (originalArray) {
  var C;
  if (isArray(originalArray)) {
    C = originalArray.constructor;
    // cross-realm fallback
    if (isConstructor(C) && (C === Array || isArray(C.prototype))) C = undefined;
    else if (isObject(C)) {
      C = C[SPECIES];
      if (C === null) C = undefined;
    }
  } return C === undefined ? Array : C;
};


/***/ }),

/***/ 14275:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/array-species-create.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var arraySpeciesConstructor = __webpack_require__(/*! ../internals/array-species-constructor */ 66511);

// `ArraySpeciesCreate` abstract operation
// https://tc39.es/ecma262/#sec-arrayspeciescreate
module.exports = function (originalArray, length) {
  return new (arraySpeciesConstructor(originalArray))(length === 0 ? 0 : length);
};


/***/ }),

/***/ 22117:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/array-unique-by.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var aCallable = __webpack_require__(/*! ../internals/a-callable */ 79366);
var lengthOfArrayLike = __webpack_require__(/*! ../internals/length-of-array-like */ 77951);
var toObject = __webpack_require__(/*! ../internals/to-object */ 73784);
var arraySpeciesCreate = __webpack_require__(/*! ../internals/array-species-create */ 14275);

var Map = getBuiltIn('Map');
var MapPrototype = Map.prototype;
var mapForEach = uncurryThis(MapPrototype.forEach);
var mapHas = uncurryThis(MapPrototype.has);
var mapSet = uncurryThis(MapPrototype.set);
var push = uncurryThis([].push);

// `Array.prototype.uniqueBy` method
// https://github.com/tc39/proposal-array-unique
module.exports = function uniqueBy(resolver) {
  var that = toObject(this);
  var length = lengthOfArrayLike(that);
  var result = arraySpeciesCreate(that, 0);
  var map = new Map();
  var resolverFunction = resolver != null ? aCallable(resolver) : function (value) {
    return value;
  };
  var index, item, key;
  for (index = 0; index < length; index++) {
    item = that[index];
    key = resolverFunction(item);
    if (!mapHas(map, key)) mapSet(map, key, item);
  }
  mapForEach(map, function (value) {
    push(result, value);
  });
  return result;
};


/***/ }),

/***/ 34430:
/*!**************************************************************************!*\
  !*** ./node_modules/core-js/internals/check-correctness-of-iteration.js ***!
  \**************************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var ITERATOR = wellKnownSymbol('iterator');
var SAFE_CLOSING = false;

try {
  var called = 0;
  var iteratorWithReturn = {
    next: function () {
      return { done: !!called++ };
    },
    'return': function () {
      SAFE_CLOSING = true;
    }
  };
  iteratorWithReturn[ITERATOR] = function () {
    return this;
  };
  // eslint-disable-next-line es/no-array-from, no-throw-literal -- required for testing
  Array.from(iteratorWithReturn, function () { throw 2; });
} catch (error) { /* empty */ }

module.exports = function (exec, SKIP_CLOSING) {
  if (!SKIP_CLOSING && !SAFE_CLOSING) return false;
  var ITERATION_SUPPORT = false;
  try {
    var object = {};
    object[ITERATOR] = function () {
      return {
        next: function () {
          return { done: ITERATION_SUPPORT = true };
        }
      };
    };
    exec(object);
  } catch (error) { /* empty */ }
  return ITERATION_SUPPORT;
};


/***/ }),

/***/ 91401:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/classof-raw.js ***!
  \*******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);

var toString = uncurryThis({}.toString);
var stringSlice = uncurryThis(''.slice);

module.exports = function (it) {
  return stringSlice(toString(it), 8, -1);
};


/***/ }),

/***/ 2212:
/*!***************************************************!*\
  !*** ./node_modules/core-js/internals/classof.js ***!
  \***************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var TO_STRING_TAG_SUPPORT = __webpack_require__(/*! ../internals/to-string-tag-support */ 85706);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var classofRaw = __webpack_require__(/*! ../internals/classof-raw */ 91401);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var TO_STRING_TAG = wellKnownSymbol('toStringTag');
var Object = global.Object;

// ES3 wrong here
var CORRECT_ARGUMENTS = classofRaw(function () { return arguments; }()) == 'Arguments';

// fallback for IE11 Script Access Denied error
var tryGet = function (it, key) {
  try {
    return it[key];
  } catch (error) { /* empty */ }
};

// getting tag from ES6+ `Object.prototype.toString`
module.exports = TO_STRING_TAG_SUPPORT ? classofRaw : function (it) {
  var O, tag, result;
  return it === undefined ? 'Undefined' : it === null ? 'Null'
    // @@toStringTag case
    : typeof (tag = tryGet(O = Object(it), TO_STRING_TAG)) == 'string' ? tag
    // builtinTag case
    : CORRECT_ARGUMENTS ? classofRaw(O)
    // ES3 arguments fallback
    : (result = classofRaw(O)) == 'Object' && isCallable(O.callee) ? 'Arguments' : result;
};


/***/ }),

/***/ 43977:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/collection-strong.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var defineProperty = (__webpack_require__(/*! ../internals/object-define-property */ 76607).f);
var create = __webpack_require__(/*! ../internals/object-create */ 1701);
var redefineAll = __webpack_require__(/*! ../internals/redefine-all */ 30924);
var bind = __webpack_require__(/*! ../internals/function-bind-context */ 90352);
var anInstance = __webpack_require__(/*! ../internals/an-instance */ 58734);
var iterate = __webpack_require__(/*! ../internals/iterate */ 99423);
var defineIterator = __webpack_require__(/*! ../internals/define-iterator */ 61115);
var setSpecies = __webpack_require__(/*! ../internals/set-species */ 84087);
var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var fastKey = (__webpack_require__(/*! ../internals/internal-metadata */ 19346).fastKey);
var InternalStateModule = __webpack_require__(/*! ../internals/internal-state */ 90979);

var setInternalState = InternalStateModule.set;
var internalStateGetterFor = InternalStateModule.getterFor;

module.exports = {
  getConstructor: function (wrapper, CONSTRUCTOR_NAME, IS_MAP, ADDER) {
    var Constructor = wrapper(function (that, iterable) {
      anInstance(that, Prototype);
      setInternalState(that, {
        type: CONSTRUCTOR_NAME,
        index: create(null),
        first: undefined,
        last: undefined,
        size: 0
      });
      if (!DESCRIPTORS) that.size = 0;
      if (iterable != undefined) iterate(iterable, that[ADDER], { that: that, AS_ENTRIES: IS_MAP });
    });

    var Prototype = Constructor.prototype;

    var getInternalState = internalStateGetterFor(CONSTRUCTOR_NAME);

    var define = function (that, key, value) {
      var state = getInternalState(that);
      var entry = getEntry(that, key);
      var previous, index;
      // change existing entry
      if (entry) {
        entry.value = value;
      // create new entry
      } else {
        state.last = entry = {
          index: index = fastKey(key, true),
          key: key,
          value: value,
          previous: previous = state.last,
          next: undefined,
          removed: false
        };
        if (!state.first) state.first = entry;
        if (previous) previous.next = entry;
        if (DESCRIPTORS) state.size++;
        else that.size++;
        // add to index
        if (index !== 'F') state.index[index] = entry;
      } return that;
    };

    var getEntry = function (that, key) {
      var state = getInternalState(that);
      // fast case
      var index = fastKey(key);
      var entry;
      if (index !== 'F') return state.index[index];
      // frozen object case
      for (entry = state.first; entry; entry = entry.next) {
        if (entry.key == key) return entry;
      }
    };

    redefineAll(Prototype, {
      // `{ Map, Set }.prototype.clear()` methods
      // https://tc39.es/ecma262/#sec-map.prototype.clear
      // https://tc39.es/ecma262/#sec-set.prototype.clear
      clear: function clear() {
        var that = this;
        var state = getInternalState(that);
        var data = state.index;
        var entry = state.first;
        while (entry) {
          entry.removed = true;
          if (entry.previous) entry.previous = entry.previous.next = undefined;
          delete data[entry.index];
          entry = entry.next;
        }
        state.first = state.last = undefined;
        if (DESCRIPTORS) state.size = 0;
        else that.size = 0;
      },
      // `{ Map, Set }.prototype.delete(key)` methods
      // https://tc39.es/ecma262/#sec-map.prototype.delete
      // https://tc39.es/ecma262/#sec-set.prototype.delete
      'delete': function (key) {
        var that = this;
        var state = getInternalState(that);
        var entry = getEntry(that, key);
        if (entry) {
          var next = entry.next;
          var prev = entry.previous;
          delete state.index[entry.index];
          entry.removed = true;
          if (prev) prev.next = next;
          if (next) next.previous = prev;
          if (state.first == entry) state.first = next;
          if (state.last == entry) state.last = prev;
          if (DESCRIPTORS) state.size--;
          else that.size--;
        } return !!entry;
      },
      // `{ Map, Set }.prototype.forEach(callbackfn, thisArg = undefined)` methods
      // https://tc39.es/ecma262/#sec-map.prototype.foreach
      // https://tc39.es/ecma262/#sec-set.prototype.foreach
      forEach: function forEach(callbackfn /* , that = undefined */) {
        var state = getInternalState(this);
        var boundFunction = bind(callbackfn, arguments.length > 1 ? arguments[1] : undefined);
        var entry;
        while (entry = entry ? entry.next : state.first) {
          boundFunction(entry.value, entry.key, this);
          // revert to the last existing entry
          while (entry && entry.removed) entry = entry.previous;
        }
      },
      // `{ Map, Set}.prototype.has(key)` methods
      // https://tc39.es/ecma262/#sec-map.prototype.has
      // https://tc39.es/ecma262/#sec-set.prototype.has
      has: function has(key) {
        return !!getEntry(this, key);
      }
    });

    redefineAll(Prototype, IS_MAP ? {
      // `Map.prototype.get(key)` method
      // https://tc39.es/ecma262/#sec-map.prototype.get
      get: function get(key) {
        var entry = getEntry(this, key);
        return entry && entry.value;
      },
      // `Map.prototype.set(key, value)` method
      // https://tc39.es/ecma262/#sec-map.prototype.set
      set: function set(key, value) {
        return define(this, key === 0 ? 0 : key, value);
      }
    } : {
      // `Set.prototype.add(value)` method
      // https://tc39.es/ecma262/#sec-set.prototype.add
      add: function add(value) {
        return define(this, value = value === 0 ? 0 : value, value);
      }
    });
    if (DESCRIPTORS) defineProperty(Prototype, 'size', {
      get: function () {
        return getInternalState(this).size;
      }
    });
    return Constructor;
  },
  setStrong: function (Constructor, CONSTRUCTOR_NAME, IS_MAP) {
    var ITERATOR_NAME = CONSTRUCTOR_NAME + ' Iterator';
    var getInternalCollectionState = internalStateGetterFor(CONSTRUCTOR_NAME);
    var getInternalIteratorState = internalStateGetterFor(ITERATOR_NAME);
    // `{ Map, Set }.prototype.{ keys, values, entries, @@iterator }()` methods
    // https://tc39.es/ecma262/#sec-map.prototype.entries
    // https://tc39.es/ecma262/#sec-map.prototype.keys
    // https://tc39.es/ecma262/#sec-map.prototype.values
    // https://tc39.es/ecma262/#sec-map.prototype-@@iterator
    // https://tc39.es/ecma262/#sec-set.prototype.entries
    // https://tc39.es/ecma262/#sec-set.prototype.keys
    // https://tc39.es/ecma262/#sec-set.prototype.values
    // https://tc39.es/ecma262/#sec-set.prototype-@@iterator
    defineIterator(Constructor, CONSTRUCTOR_NAME, function (iterated, kind) {
      setInternalState(this, {
        type: ITERATOR_NAME,
        target: iterated,
        state: getInternalCollectionState(iterated),
        kind: kind,
        last: undefined
      });
    }, function () {
      var state = getInternalIteratorState(this);
      var kind = state.kind;
      var entry = state.last;
      // revert to the last existing entry
      while (entry && entry.removed) entry = entry.previous;
      // get next entry
      if (!state.target || !(state.last = entry = entry ? entry.next : state.state.first)) {
        // or finish the iteration
        state.target = undefined;
        return { value: undefined, done: true };
      }
      // return step by kind
      if (kind == 'keys') return { value: entry.key, done: false };
      if (kind == 'values') return { value: entry.value, done: false };
      return { value: [entry.key, entry.value], done: false };
    }, IS_MAP ? 'entries' : 'values', !IS_MAP, true);

    // `{ Map, Set }.prototype[@@species]` accessors
    // https://tc39.es/ecma262/#sec-get-map-@@species
    // https://tc39.es/ecma262/#sec-get-set-@@species
    setSpecies(CONSTRUCTOR_NAME);
  }
};


/***/ }),

/***/ 61725:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/collection-weak.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var redefineAll = __webpack_require__(/*! ../internals/redefine-all */ 30924);
var getWeakData = (__webpack_require__(/*! ../internals/internal-metadata */ 19346).getWeakData);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var anInstance = __webpack_require__(/*! ../internals/an-instance */ 58734);
var iterate = __webpack_require__(/*! ../internals/iterate */ 99423);
var ArrayIterationModule = __webpack_require__(/*! ../internals/array-iteration */ 98657);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var InternalStateModule = __webpack_require__(/*! ../internals/internal-state */ 90979);

var setInternalState = InternalStateModule.set;
var internalStateGetterFor = InternalStateModule.getterFor;
var find = ArrayIterationModule.find;
var findIndex = ArrayIterationModule.findIndex;
var splice = uncurryThis([].splice);
var id = 0;

// fallback for uncaught frozen keys
var uncaughtFrozenStore = function (store) {
  return store.frozen || (store.frozen = new UncaughtFrozenStore());
};

var UncaughtFrozenStore = function () {
  this.entries = [];
};

var findUncaughtFrozen = function (store, key) {
  return find(store.entries, function (it) {
    return it[0] === key;
  });
};

UncaughtFrozenStore.prototype = {
  get: function (key) {
    var entry = findUncaughtFrozen(this, key);
    if (entry) return entry[1];
  },
  has: function (key) {
    return !!findUncaughtFrozen(this, key);
  },
  set: function (key, value) {
    var entry = findUncaughtFrozen(this, key);
    if (entry) entry[1] = value;
    else this.entries.push([key, value]);
  },
  'delete': function (key) {
    var index = findIndex(this.entries, function (it) {
      return it[0] === key;
    });
    if (~index) splice(this.entries, index, 1);
    return !!~index;
  }
};

module.exports = {
  getConstructor: function (wrapper, CONSTRUCTOR_NAME, IS_MAP, ADDER) {
    var Constructor = wrapper(function (that, iterable) {
      anInstance(that, Prototype);
      setInternalState(that, {
        type: CONSTRUCTOR_NAME,
        id: id++,
        frozen: undefined
      });
      if (iterable != undefined) iterate(iterable, that[ADDER], { that: that, AS_ENTRIES: IS_MAP });
    });

    var Prototype = Constructor.prototype;

    var getInternalState = internalStateGetterFor(CONSTRUCTOR_NAME);

    var define = function (that, key, value) {
      var state = getInternalState(that);
      var data = getWeakData(anObject(key), true);
      if (data === true) uncaughtFrozenStore(state).set(key, value);
      else data[state.id] = value;
      return that;
    };

    redefineAll(Prototype, {
      // `{ WeakMap, WeakSet }.prototype.delete(key)` methods
      // https://tc39.es/ecma262/#sec-weakmap.prototype.delete
      // https://tc39.es/ecma262/#sec-weakset.prototype.delete
      'delete': function (key) {
        var state = getInternalState(this);
        if (!isObject(key)) return false;
        var data = getWeakData(key);
        if (data === true) return uncaughtFrozenStore(state)['delete'](key);
        return data && hasOwn(data, state.id) && delete data[state.id];
      },
      // `{ WeakMap, WeakSet }.prototype.has(key)` methods
      // https://tc39.es/ecma262/#sec-weakmap.prototype.has
      // https://tc39.es/ecma262/#sec-weakset.prototype.has
      has: function has(key) {
        var state = getInternalState(this);
        if (!isObject(key)) return false;
        var data = getWeakData(key);
        if (data === true) return uncaughtFrozenStore(state).has(key);
        return data && hasOwn(data, state.id);
      }
    });

    redefineAll(Prototype, IS_MAP ? {
      // `WeakMap.prototype.get(key)` method
      // https://tc39.es/ecma262/#sec-weakmap.prototype.get
      get: function get(key) {
        var state = getInternalState(this);
        if (isObject(key)) {
          var data = getWeakData(key);
          if (data === true) return uncaughtFrozenStore(state).get(key);
          return data ? data[state.id] : undefined;
        }
      },
      // `WeakMap.prototype.set(key, value)` method
      // https://tc39.es/ecma262/#sec-weakmap.prototype.set
      set: function set(key, value) {
        return define(this, key, value);
      }
    } : {
      // `WeakSet.prototype.add(value)` method
      // https://tc39.es/ecma262/#sec-weakset.prototype.add
      add: function add(value) {
        return define(this, value, true);
      }
    });

    return Constructor;
  }
};


/***/ }),

/***/ 87126:
/*!******************************************************!*\
  !*** ./node_modules/core-js/internals/collection.js ***!
  \******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var $ = __webpack_require__(/*! ../internals/export */ 664);
var global = __webpack_require__(/*! ../internals/global */ 94720);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var isForced = __webpack_require__(/*! ../internals/is-forced */ 89545);
var redefine = __webpack_require__(/*! ../internals/redefine */ 56292);
var InternalMetadataModule = __webpack_require__(/*! ../internals/internal-metadata */ 19346);
var iterate = __webpack_require__(/*! ../internals/iterate */ 99423);
var anInstance = __webpack_require__(/*! ../internals/an-instance */ 58734);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var checkCorrectnessOfIteration = __webpack_require__(/*! ../internals/check-correctness-of-iteration */ 34430);
var setToStringTag = __webpack_require__(/*! ../internals/set-to-string-tag */ 15387);
var inheritIfRequired = __webpack_require__(/*! ../internals/inherit-if-required */ 51072);

module.exports = function (CONSTRUCTOR_NAME, wrapper, common) {
  var IS_MAP = CONSTRUCTOR_NAME.indexOf('Map') !== -1;
  var IS_WEAK = CONSTRUCTOR_NAME.indexOf('Weak') !== -1;
  var ADDER = IS_MAP ? 'set' : 'add';
  var NativeConstructor = global[CONSTRUCTOR_NAME];
  var NativePrototype = NativeConstructor && NativeConstructor.prototype;
  var Constructor = NativeConstructor;
  var exported = {};

  var fixMethod = function (KEY) {
    var uncurriedNativeMethod = uncurryThis(NativePrototype[KEY]);
    redefine(NativePrototype, KEY,
      KEY == 'add' ? function add(value) {
        uncurriedNativeMethod(this, value === 0 ? 0 : value);
        return this;
      } : KEY == 'delete' ? function (key) {
        return IS_WEAK && !isObject(key) ? false : uncurriedNativeMethod(this, key === 0 ? 0 : key);
      } : KEY == 'get' ? function get(key) {
        return IS_WEAK && !isObject(key) ? undefined : uncurriedNativeMethod(this, key === 0 ? 0 : key);
      } : KEY == 'has' ? function has(key) {
        return IS_WEAK && !isObject(key) ? false : uncurriedNativeMethod(this, key === 0 ? 0 : key);
      } : function set(key, value) {
        uncurriedNativeMethod(this, key === 0 ? 0 : key, value);
        return this;
      }
    );
  };

  var REPLACE = isForced(
    CONSTRUCTOR_NAME,
    !isCallable(NativeConstructor) || !(IS_WEAK || NativePrototype.forEach && !fails(function () {
      new NativeConstructor().entries().next();
    }))
  );

  if (REPLACE) {
    // create collection constructor
    Constructor = common.getConstructor(wrapper, CONSTRUCTOR_NAME, IS_MAP, ADDER);
    InternalMetadataModule.enable();
  } else if (isForced(CONSTRUCTOR_NAME, true)) {
    var instance = new Constructor();
    // early implementations not supports chaining
    var HASNT_CHAINING = instance[ADDER](IS_WEAK ? {} : -0, 1) != instance;
    // V8 ~ Chromium 40- weak-collections throws on primitives, but should return false
    var THROWS_ON_PRIMITIVES = fails(function () { instance.has(1); });
    // most early implementations doesn't supports iterables, most modern - not close it correctly
    // eslint-disable-next-line no-new -- required for testing
    var ACCEPT_ITERABLES = checkCorrectnessOfIteration(function (iterable) { new NativeConstructor(iterable); });
    // for early implementations -0 and +0 not the same
    var BUGGY_ZERO = !IS_WEAK && fails(function () {
      // V8 ~ Chromium 42- fails only with 5+ elements
      var $instance = new NativeConstructor();
      var index = 5;
      while (index--) $instance[ADDER](index, index);
      return !$instance.has(-0);
    });

    if (!ACCEPT_ITERABLES) {
      Constructor = wrapper(function (dummy, iterable) {
        anInstance(dummy, NativePrototype);
        var that = inheritIfRequired(new NativeConstructor(), dummy, Constructor);
        if (iterable != undefined) iterate(iterable, that[ADDER], { that: that, AS_ENTRIES: IS_MAP });
        return that;
      });
      Constructor.prototype = NativePrototype;
      NativePrototype.constructor = Constructor;
    }

    if (THROWS_ON_PRIMITIVES || BUGGY_ZERO) {
      fixMethod('delete');
      fixMethod('has');
      IS_MAP && fixMethod('get');
    }

    if (BUGGY_ZERO || HASNT_CHAINING) fixMethod(ADDER);

    // weak collections should not contains .clear method
    if (IS_WEAK && NativePrototype.clear) delete NativePrototype.clear;
  }

  exported[CONSTRUCTOR_NAME] = Constructor;
  $({ global: true, forced: Constructor != NativeConstructor }, exported);

  setToStringTag(Constructor, CONSTRUCTOR_NAME);

  if (!IS_WEAK) common.setStrong(Constructor, CONSTRUCTOR_NAME, IS_MAP);

  return Constructor;
};


/***/ }),

/***/ 81975:
/*!***********************************************************************!*\
  !*** ./node_modules/core-js/internals/copy-constructor-properties.js ***!
  \***********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var ownKeys = __webpack_require__(/*! ../internals/own-keys */ 24432);
var getOwnPropertyDescriptorModule = __webpack_require__(/*! ../internals/object-get-own-property-descriptor */ 50313);
var definePropertyModule = __webpack_require__(/*! ../internals/object-define-property */ 76607);

module.exports = function (target, source, exceptions) {
  var keys = ownKeys(source);
  var defineProperty = definePropertyModule.f;
  var getOwnPropertyDescriptor = getOwnPropertyDescriptorModule.f;
  for (var i = 0; i < keys.length; i++) {
    var key = keys[i];
    if (!hasOwn(target, key) && !(exceptions && hasOwn(exceptions, key))) {
      defineProperty(target, key, getOwnPropertyDescriptor(source, key));
    }
  }
};


/***/ }),

/***/ 68745:
/*!********************************************************************!*\
  !*** ./node_modules/core-js/internals/correct-prototype-getter.js ***!
  \********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);

module.exports = !fails(function () {
  function F() { /* empty */ }
  F.prototype.constructor = null;
  // eslint-disable-next-line es/no-object-getprototypeof -- required for testing
  return Object.getPrototypeOf(new F()) !== F.prototype;
});


/***/ }),

/***/ 44298:
/*!***********************************************************************!*\
  !*** ./node_modules/core-js/internals/create-iterator-constructor.js ***!
  \***********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var IteratorPrototype = (__webpack_require__(/*! ../internals/iterators-core */ 12753).IteratorPrototype);
var create = __webpack_require__(/*! ../internals/object-create */ 1701);
var createPropertyDescriptor = __webpack_require__(/*! ../internals/create-property-descriptor */ 8808);
var setToStringTag = __webpack_require__(/*! ../internals/set-to-string-tag */ 15387);
var Iterators = __webpack_require__(/*! ../internals/iterators */ 88424);

var returnThis = function () { return this; };

module.exports = function (IteratorConstructor, NAME, next, ENUMERABLE_NEXT) {
  var TO_STRING_TAG = NAME + ' Iterator';
  IteratorConstructor.prototype = create(IteratorPrototype, { next: createPropertyDescriptor(+!ENUMERABLE_NEXT, next) });
  setToStringTag(IteratorConstructor, TO_STRING_TAG, false, true);
  Iterators[TO_STRING_TAG] = returnThis;
  return IteratorConstructor;
};


/***/ }),

/***/ 33779:
/*!**************************************************************************!*\
  !*** ./node_modules/core-js/internals/create-non-enumerable-property.js ***!
  \**************************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var definePropertyModule = __webpack_require__(/*! ../internals/object-define-property */ 76607);
var createPropertyDescriptor = __webpack_require__(/*! ../internals/create-property-descriptor */ 8808);

module.exports = DESCRIPTORS ? function (object, key, value) {
  return definePropertyModule.f(object, key, createPropertyDescriptor(1, value));
} : function (object, key, value) {
  object[key] = value;
  return object;
};


/***/ }),

/***/ 8808:
/*!**********************************************************************!*\
  !*** ./node_modules/core-js/internals/create-property-descriptor.js ***!
  \**********************************************************************/
/***/ ((module) => {

module.exports = function (bitmap, value) {
  return {
    enumerable: !(bitmap & 1),
    configurable: !(bitmap & 2),
    writable: !(bitmap & 4),
    value: value
  };
};


/***/ }),

/***/ 67182:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/create-property.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var toPropertyKey = __webpack_require__(/*! ../internals/to-property-key */ 12637);
var definePropertyModule = __webpack_require__(/*! ../internals/object-define-property */ 76607);
var createPropertyDescriptor = __webpack_require__(/*! ../internals/create-property-descriptor */ 8808);

module.exports = function (object, key, value) {
  var propertyKey = toPropertyKey(key);
  if (propertyKey in object) definePropertyModule.f(object, propertyKey, createPropertyDescriptor(0, value));
  else object[propertyKey] = value;
};


/***/ }),

/***/ 61115:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/define-iterator.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var $ = __webpack_require__(/*! ../internals/export */ 664);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var IS_PURE = __webpack_require__(/*! ../internals/is-pure */ 42074);
var FunctionName = __webpack_require__(/*! ../internals/function-name */ 4513);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var createIteratorConstructor = __webpack_require__(/*! ../internals/create-iterator-constructor */ 44298);
var getPrototypeOf = __webpack_require__(/*! ../internals/object-get-prototype-of */ 45456);
var setPrototypeOf = __webpack_require__(/*! ../internals/object-set-prototype-of */ 86736);
var setToStringTag = __webpack_require__(/*! ../internals/set-to-string-tag */ 15387);
var createNonEnumerableProperty = __webpack_require__(/*! ../internals/create-non-enumerable-property */ 33779);
var redefine = __webpack_require__(/*! ../internals/redefine */ 56292);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);
var Iterators = __webpack_require__(/*! ../internals/iterators */ 88424);
var IteratorsCore = __webpack_require__(/*! ../internals/iterators-core */ 12753);

var PROPER_FUNCTION_NAME = FunctionName.PROPER;
var CONFIGURABLE_FUNCTION_NAME = FunctionName.CONFIGURABLE;
var IteratorPrototype = IteratorsCore.IteratorPrototype;
var BUGGY_SAFARI_ITERATORS = IteratorsCore.BUGGY_SAFARI_ITERATORS;
var ITERATOR = wellKnownSymbol('iterator');
var KEYS = 'keys';
var VALUES = 'values';
var ENTRIES = 'entries';

var returnThis = function () { return this; };

module.exports = function (Iterable, NAME, IteratorConstructor, next, DEFAULT, IS_SET, FORCED) {
  createIteratorConstructor(IteratorConstructor, NAME, next);

  var getIterationMethod = function (KIND) {
    if (KIND === DEFAULT && defaultIterator) return defaultIterator;
    if (!BUGGY_SAFARI_ITERATORS && KIND in IterablePrototype) return IterablePrototype[KIND];
    switch (KIND) {
      case KEYS: return function keys() { return new IteratorConstructor(this, KIND); };
      case VALUES: return function values() { return new IteratorConstructor(this, KIND); };
      case ENTRIES: return function entries() { return new IteratorConstructor(this, KIND); };
    } return function () { return new IteratorConstructor(this); };
  };

  var TO_STRING_TAG = NAME + ' Iterator';
  var INCORRECT_VALUES_NAME = false;
  var IterablePrototype = Iterable.prototype;
  var nativeIterator = IterablePrototype[ITERATOR]
    || IterablePrototype['@@iterator']
    || DEFAULT && IterablePrototype[DEFAULT];
  var defaultIterator = !BUGGY_SAFARI_ITERATORS && nativeIterator || getIterationMethod(DEFAULT);
  var anyNativeIterator = NAME == 'Array' ? IterablePrototype.entries || nativeIterator : nativeIterator;
  var CurrentIteratorPrototype, methods, KEY;

  // fix native
  if (anyNativeIterator) {
    CurrentIteratorPrototype = getPrototypeOf(anyNativeIterator.call(new Iterable()));
    if (CurrentIteratorPrototype !== Object.prototype && CurrentIteratorPrototype.next) {
      if (!IS_PURE && getPrototypeOf(CurrentIteratorPrototype) !== IteratorPrototype) {
        if (setPrototypeOf) {
          setPrototypeOf(CurrentIteratorPrototype, IteratorPrototype);
        } else if (!isCallable(CurrentIteratorPrototype[ITERATOR])) {
          redefine(CurrentIteratorPrototype, ITERATOR, returnThis);
        }
      }
      // Set @@toStringTag to native iterators
      setToStringTag(CurrentIteratorPrototype, TO_STRING_TAG, true, true);
      if (IS_PURE) Iterators[TO_STRING_TAG] = returnThis;
    }
  }

  // fix Array.prototype.{ values, @@iterator }.name in V8 / FF
  if (PROPER_FUNCTION_NAME && DEFAULT == VALUES && nativeIterator && nativeIterator.name !== VALUES) {
    if (!IS_PURE && CONFIGURABLE_FUNCTION_NAME) {
      createNonEnumerableProperty(IterablePrototype, 'name', VALUES);
    } else {
      INCORRECT_VALUES_NAME = true;
      defaultIterator = function values() { return call(nativeIterator, this); };
    }
  }

  // export additional methods
  if (DEFAULT) {
    methods = {
      values: getIterationMethod(VALUES),
      keys: IS_SET ? defaultIterator : getIterationMethod(KEYS),
      entries: getIterationMethod(ENTRIES)
    };
    if (FORCED) for (KEY in methods) {
      if (BUGGY_SAFARI_ITERATORS || INCORRECT_VALUES_NAME || !(KEY in IterablePrototype)) {
        redefine(IterablePrototype, KEY, methods[KEY]);
      }
    } else $({ target: NAME, proto: true, forced: BUGGY_SAFARI_ITERATORS || INCORRECT_VALUES_NAME }, methods);
  }

  // define iterator
  if ((!IS_PURE || FORCED) && IterablePrototype[ITERATOR] !== defaultIterator) {
    redefine(IterablePrototype, ITERATOR, defaultIterator, { name: DEFAULT });
  }
  Iterators[NAME] = defaultIterator;

  return methods;
};


/***/ }),

/***/ 45601:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/descriptors.js ***!
  \*******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);

// Detect IE8's incomplete defineProperty implementation
module.exports = !fails(function () {
  // eslint-disable-next-line es/no-object-defineproperty -- required for testing
  return Object.defineProperty({}, 1, { get: function () { return 7; } })[1] != 7;
});


/***/ }),

/***/ 77783:
/*!*******************************************************************!*\
  !*** ./node_modules/core-js/internals/document-create-element.js ***!
  \*******************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);

var document = global.document;
// typeof document.createElement is 'object' in old IE
var EXISTS = isObject(document) && isObject(document.createElement);

module.exports = function (it) {
  return EXISTS ? document.createElement(it) : {};
};


/***/ }),

/***/ 78684:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/engine-user-agent.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);

module.exports = getBuiltIn('navigator', 'userAgent') || '';


/***/ }),

/***/ 39905:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/engine-v8-version.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var userAgent = __webpack_require__(/*! ../internals/engine-user-agent */ 78684);

var process = global.process;
var Deno = global.Deno;
var versions = process && process.versions || Deno && Deno.version;
var v8 = versions && versions.v8;
var match, version;

if (v8) {
  match = v8.split('.');
  // in old Chrome, versions of V8 isn't V8 = Chrome / 10
  // but their correct versions are not interesting for us
  version = match[0] > 0 && match[0] < 4 ? 1 : +(match[0] + match[1]);
}

// BrowserFS NodeJS `process` polyfill incorrectly set `.v8` to `0.0`
// so check `userAgent` even if `.v8` exists, but 0
if (!version && userAgent) {
  match = userAgent.match(/Edge\/(\d+)/);
  if (!match || match[1] >= 74) {
    match = userAgent.match(/Chrome\/(\d+)/);
    if (match) version = +match[1];
  }
}

module.exports = version;


/***/ }),

/***/ 90256:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/enum-bug-keys.js ***!
  \*********************************************************/
/***/ ((module) => {

// IE8- don't enum bug keys
module.exports = [
  'constructor',
  'hasOwnProperty',
  'isPrototypeOf',
  'propertyIsEnumerable',
  'toLocaleString',
  'toString',
  'valueOf'
];


/***/ }),

/***/ 664:
/*!**************************************************!*\
  !*** ./node_modules/core-js/internals/export.js ***!
  \**************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var getOwnPropertyDescriptor = (__webpack_require__(/*! ../internals/object-get-own-property-descriptor */ 50313).f);
var createNonEnumerableProperty = __webpack_require__(/*! ../internals/create-non-enumerable-property */ 33779);
var redefine = __webpack_require__(/*! ../internals/redefine */ 56292);
var setGlobal = __webpack_require__(/*! ../internals/set-global */ 85123);
var copyConstructorProperties = __webpack_require__(/*! ../internals/copy-constructor-properties */ 81975);
var isForced = __webpack_require__(/*! ../internals/is-forced */ 89545);

/*
  options.target      - name of the target object
  options.global      - target is the global object
  options.stat        - export as static methods of target
  options.proto       - export as prototype methods of target
  options.real        - real prototype method for the `pure` version
  options.forced      - export even if the native feature is available
  options.bind        - bind methods to the target, required for the `pure` version
  options.wrap        - wrap constructors to preventing global pollution, required for the `pure` version
  options.unsafe      - use the simple assignment of property instead of delete + defineProperty
  options.sham        - add a flag to not completely full polyfills
  options.enumerable  - export as enumerable property
  options.noTargetGet - prevent calling a getter on target
  options.name        - the .name of the function if it does not match the key
*/
module.exports = function (options, source) {
  var TARGET = options.target;
  var GLOBAL = options.global;
  var STATIC = options.stat;
  var FORCED, target, key, targetProperty, sourceProperty, descriptor;
  if (GLOBAL) {
    target = global;
  } else if (STATIC) {
    target = global[TARGET] || setGlobal(TARGET, {});
  } else {
    target = (global[TARGET] || {}).prototype;
  }
  if (target) for (key in source) {
    sourceProperty = source[key];
    if (options.noTargetGet) {
      descriptor = getOwnPropertyDescriptor(target, key);
      targetProperty = descriptor && descriptor.value;
    } else targetProperty = target[key];
    FORCED = isForced(GLOBAL ? key : TARGET + (STATIC ? '.' : '#') + key, options.forced);
    // contained in target
    if (!FORCED && targetProperty !== undefined) {
      if (typeof sourceProperty == typeof targetProperty) continue;
      copyConstructorProperties(sourceProperty, targetProperty);
    }
    // add a flag to not completely full polyfills
    if (options.sham || (targetProperty && targetProperty.sham)) {
      createNonEnumerableProperty(sourceProperty, 'sham', true);
    }
    // extend global
    redefine(target, key, sourceProperty, options);
  }
};


/***/ }),

/***/ 27944:
/*!*************************************************!*\
  !*** ./node_modules/core-js/internals/fails.js ***!
  \*************************************************/
/***/ ((module) => {

module.exports = function (exec) {
  try {
    return !!exec();
  } catch (error) {
    return true;
  }
};


/***/ }),

/***/ 2655:
/*!****************************************************!*\
  !*** ./node_modules/core-js/internals/freezing.js ***!
  \****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);

module.exports = !fails(function () {
  // eslint-disable-next-line es/no-object-isextensible, es/no-object-preventextensions -- required for testing
  return Object.isExtensible(Object.preventExtensions({}));
});


/***/ }),

/***/ 90352:
/*!*****************************************************************!*\
  !*** ./node_modules/core-js/internals/function-bind-context.js ***!
  \*****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var aCallable = __webpack_require__(/*! ../internals/a-callable */ 79366);
var NATIVE_BIND = __webpack_require__(/*! ../internals/function-bind-native */ 27181);

var bind = uncurryThis(uncurryThis.bind);

// optional / simple context binding
module.exports = function (fn, that) {
  aCallable(fn);
  return that === undefined ? fn : NATIVE_BIND ? bind(fn, that) : function (/* ...args */) {
    return fn.apply(that, arguments);
  };
};


/***/ }),

/***/ 27181:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/function-bind-native.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);

module.exports = !fails(function () {
  var test = (function () { /* empty */ }).bind();
  // eslint-disable-next-line no-prototype-builtins -- safe
  return typeof test != 'function' || test.hasOwnProperty('prototype');
});


/***/ }),

/***/ 39836:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/function-call.js ***!
  \*********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var NATIVE_BIND = __webpack_require__(/*! ../internals/function-bind-native */ 27181);

var call = Function.prototype.call;

module.exports = NATIVE_BIND ? call.bind(call) : function () {
  return call.apply(call, arguments);
};


/***/ }),

/***/ 4513:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/function-name.js ***!
  \*********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);

var FunctionPrototype = Function.prototype;
// eslint-disable-next-line es/no-object-getownpropertydescriptor -- safe
var getDescriptor = DESCRIPTORS && Object.getOwnPropertyDescriptor;

var EXISTS = hasOwn(FunctionPrototype, 'name');
// additional protection from minified / mangled / dropped function names
var PROPER = EXISTS && (function something() { /* empty */ }).name === 'something';
var CONFIGURABLE = EXISTS && (!DESCRIPTORS || (DESCRIPTORS && getDescriptor(FunctionPrototype, 'name').configurable));

module.exports = {
  EXISTS: EXISTS,
  PROPER: PROPER,
  CONFIGURABLE: CONFIGURABLE
};


/***/ }),

/***/ 42006:
/*!*****************************************************************!*\
  !*** ./node_modules/core-js/internals/function-uncurry-this.js ***!
  \*****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var NATIVE_BIND = __webpack_require__(/*! ../internals/function-bind-native */ 27181);

var FunctionPrototype = Function.prototype;
var bind = FunctionPrototype.bind;
var call = FunctionPrototype.call;
var uncurryThis = NATIVE_BIND && bind.bind(call, call);

module.exports = NATIVE_BIND ? function (fn) {
  return fn && uncurryThis(fn);
} : function (fn) {
  return fn && function () {
    return call.apply(fn, arguments);
  };
};


/***/ }),

/***/ 2149:
/*!********************************************************!*\
  !*** ./node_modules/core-js/internals/get-built-in.js ***!
  \********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);

var aFunction = function (argument) {
  return isCallable(argument) ? argument : undefined;
};

module.exports = function (namespace, method) {
  return arguments.length < 2 ? aFunction(global[namespace]) : global[namespace] && global[namespace][method];
};


/***/ }),

/***/ 76644:
/*!***************************************************************!*\
  !*** ./node_modules/core-js/internals/get-iterator-method.js ***!
  \***************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var classof = __webpack_require__(/*! ../internals/classof */ 2212);
var getMethod = __webpack_require__(/*! ../internals/get-method */ 32383);
var Iterators = __webpack_require__(/*! ../internals/iterators */ 88424);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var ITERATOR = wellKnownSymbol('iterator');

module.exports = function (it) {
  if (it != undefined) return getMethod(it, ITERATOR)
    || getMethod(it, '@@iterator')
    || Iterators[classof(it)];
};


/***/ }),

/***/ 66029:
/*!********************************************************!*\
  !*** ./node_modules/core-js/internals/get-iterator.js ***!
  \********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var aCallable = __webpack_require__(/*! ../internals/a-callable */ 79366);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var tryToString = __webpack_require__(/*! ../internals/try-to-string */ 67366);
var getIteratorMethod = __webpack_require__(/*! ../internals/get-iterator-method */ 76644);

var TypeError = global.TypeError;

module.exports = function (argument, usingIterator) {
  var iteratorMethod = arguments.length < 2 ? getIteratorMethod(argument) : usingIterator;
  if (aCallable(iteratorMethod)) return anObject(call(iteratorMethod, argument));
  throw TypeError(tryToString(argument) + ' is not iterable');
};


/***/ }),

/***/ 32383:
/*!******************************************************!*\
  !*** ./node_modules/core-js/internals/get-method.js ***!
  \******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var aCallable = __webpack_require__(/*! ../internals/a-callable */ 79366);

// `GetMethod` abstract operation
// https://tc39.es/ecma262/#sec-getmethod
module.exports = function (V, P) {
  var func = V[P];
  return func == null ? undefined : aCallable(func);
};


/***/ }),

/***/ 94720:
/*!**************************************************!*\
  !*** ./node_modules/core-js/internals/global.js ***!
  \**************************************************/
/***/ ((module) => {

var check = function (it) {
  return it && it.Math == Math && it;
};

// https://github.com/zloirock/core-js/issues/86#issuecomment-115759028
module.exports =
  // eslint-disable-next-line es/no-global-this -- safe
  check(typeof globalThis == 'object' && globalThis) ||
  check(typeof window == 'object' && window) ||
  // eslint-disable-next-line no-restricted-globals -- safe
  check(typeof self == 'object' && self) ||
  check(typeof global == 'object' && global) ||
  // eslint-disable-next-line no-new-func -- fallback
  (function () { return this; })() || Function('return this')();


/***/ }),

/***/ 8578:
/*!************************************************************!*\
  !*** ./node_modules/core-js/internals/has-own-property.js ***!
  \************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var toObject = __webpack_require__(/*! ../internals/to-object */ 73784);

var hasOwnProperty = uncurryThis({}.hasOwnProperty);

// `HasOwnProperty` abstract operation
// https://tc39.es/ecma262/#sec-hasownproperty
module.exports = Object.hasOwn || function hasOwn(it, key) {
  return hasOwnProperty(toObject(it), key);
};


/***/ }),

/***/ 77086:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/hidden-keys.js ***!
  \*******************************************************/
/***/ ((module) => {

module.exports = {};


/***/ }),

/***/ 34989:
/*!************************************************!*\
  !*** ./node_modules/core-js/internals/html.js ***!
  \************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);

module.exports = getBuiltIn('document', 'documentElement');


/***/ }),

/***/ 5873:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/ie8-dom-define.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var createElement = __webpack_require__(/*! ../internals/document-create-element */ 77783);

// Thanks to IE8 for its funny defineProperty
module.exports = !DESCRIPTORS && !fails(function () {
  // eslint-disable-next-line es/no-object-defineproperty -- required for testing
  return Object.defineProperty(createElement('div'), 'a', {
    get: function () { return 7; }
  }).a != 7;
});


/***/ }),

/***/ 32462:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/indexed-object.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var classof = __webpack_require__(/*! ../internals/classof-raw */ 91401);

var Object = global.Object;
var split = uncurryThis(''.split);

// fallback for non-array-like ES3 and non-enumerable old V8 strings
module.exports = fails(function () {
  // throws an error in rhino, see https://github.com/mozilla/rhino/issues/346
  // eslint-disable-next-line no-prototype-builtins -- safe
  return !Object('z').propertyIsEnumerable(0);
}) ? function (it) {
  return classof(it) == 'String' ? split(it, '') : Object(it);
} : Object;


/***/ }),

/***/ 51072:
/*!***************************************************************!*\
  !*** ./node_modules/core-js/internals/inherit-if-required.js ***!
  \***************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var setPrototypeOf = __webpack_require__(/*! ../internals/object-set-prototype-of */ 86736);

// makes subclassing work correct for wrapped built-ins
module.exports = function ($this, dummy, Wrapper) {
  var NewTarget, NewTargetPrototype;
  if (
    // it can work only with native `setPrototypeOf`
    setPrototypeOf &&
    // we haven't completely correct pre-ES6 way for getting `new.target`, so use this
    isCallable(NewTarget = dummy.constructor) &&
    NewTarget !== Wrapper &&
    isObject(NewTargetPrototype = NewTarget.prototype) &&
    NewTargetPrototype !== Wrapper.prototype
  ) setPrototypeOf($this, NewTargetPrototype);
  return $this;
};


/***/ }),

/***/ 56073:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/inspect-source.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var store = __webpack_require__(/*! ../internals/shared-store */ 35691);

var functionToString = uncurryThis(Function.toString);

// this helper broken in `core-js@3.4.1-3.4.4`, so we can't use `shared` helper
if (!isCallable(store.inspectSource)) {
  store.inspectSource = function (it) {
    return functionToString(it);
  };
}

module.exports = store.inspectSource;


/***/ }),

/***/ 19346:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/internal-metadata.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var hiddenKeys = __webpack_require__(/*! ../internals/hidden-keys */ 77086);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var defineProperty = (__webpack_require__(/*! ../internals/object-define-property */ 76607).f);
var getOwnPropertyNamesModule = __webpack_require__(/*! ../internals/object-get-own-property-names */ 24283);
var getOwnPropertyNamesExternalModule = __webpack_require__(/*! ../internals/object-get-own-property-names-external */ 53138);
var isExtensible = __webpack_require__(/*! ../internals/object-is-extensible */ 53892);
var uid = __webpack_require__(/*! ../internals/uid */ 1387);
var FREEZING = __webpack_require__(/*! ../internals/freezing */ 2655);

var REQUIRED = false;
var METADATA = uid('meta');
var id = 0;

var setMetadata = function (it) {
  defineProperty(it, METADATA, { value: {
    objectID: 'O' + id++, // object ID
    weakData: {}          // weak collections IDs
  } });
};

var fastKey = function (it, create) {
  // return a primitive with prefix
  if (!isObject(it)) return typeof it == 'symbol' ? it : (typeof it == 'string' ? 'S' : 'P') + it;
  if (!hasOwn(it, METADATA)) {
    // can't set metadata to uncaught frozen object
    if (!isExtensible(it)) return 'F';
    // not necessary to add metadata
    if (!create) return 'E';
    // add missing metadata
    setMetadata(it);
  // return object ID
  } return it[METADATA].objectID;
};

var getWeakData = function (it, create) {
  if (!hasOwn(it, METADATA)) {
    // can't set metadata to uncaught frozen object
    if (!isExtensible(it)) return true;
    // not necessary to add metadata
    if (!create) return false;
    // add missing metadata
    setMetadata(it);
  // return the store of weak collections IDs
  } return it[METADATA].weakData;
};

// add metadata on freeze-family methods calling
var onFreeze = function (it) {
  if (FREEZING && REQUIRED && isExtensible(it) && !hasOwn(it, METADATA)) setMetadata(it);
  return it;
};

var enable = function () {
  meta.enable = function () { /* empty */ };
  REQUIRED = true;
  var getOwnPropertyNames = getOwnPropertyNamesModule.f;
  var splice = uncurryThis([].splice);
  var test = {};
  test[METADATA] = 1;

  // prevent exposing of metadata key
  if (getOwnPropertyNames(test).length) {
    getOwnPropertyNamesModule.f = function (it) {
      var result = getOwnPropertyNames(it);
      for (var i = 0, length = result.length; i < length; i++) {
        if (result[i] === METADATA) {
          splice(result, i, 1);
          break;
        }
      } return result;
    };

    $({ target: 'Object', stat: true, forced: true }, {
      getOwnPropertyNames: getOwnPropertyNamesExternalModule.f
    });
  }
};

var meta = module.exports = {
  enable: enable,
  fastKey: fastKey,
  getWeakData: getWeakData,
  onFreeze: onFreeze
};

hiddenKeys[METADATA] = true;


/***/ }),

/***/ 90979:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/internal-state.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var NATIVE_WEAK_MAP = __webpack_require__(/*! ../internals/native-weak-map */ 90898);
var global = __webpack_require__(/*! ../internals/global */ 94720);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var createNonEnumerableProperty = __webpack_require__(/*! ../internals/create-non-enumerable-property */ 33779);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var shared = __webpack_require__(/*! ../internals/shared-store */ 35691);
var sharedKey = __webpack_require__(/*! ../internals/shared-key */ 49660);
var hiddenKeys = __webpack_require__(/*! ../internals/hidden-keys */ 77086);

var OBJECT_ALREADY_INITIALIZED = 'Object already initialized';
var TypeError = global.TypeError;
var WeakMap = global.WeakMap;
var set, get, has;

var enforce = function (it) {
  return has(it) ? get(it) : set(it, {});
};

var getterFor = function (TYPE) {
  return function (it) {
    var state;
    if (!isObject(it) || (state = get(it)).type !== TYPE) {
      throw TypeError('Incompatible receiver, ' + TYPE + ' required');
    } return state;
  };
};

if (NATIVE_WEAK_MAP || shared.state) {
  var store = shared.state || (shared.state = new WeakMap());
  var wmget = uncurryThis(store.get);
  var wmhas = uncurryThis(store.has);
  var wmset = uncurryThis(store.set);
  set = function (it, metadata) {
    if (wmhas(store, it)) throw new TypeError(OBJECT_ALREADY_INITIALIZED);
    metadata.facade = it;
    wmset(store, it, metadata);
    return metadata;
  };
  get = function (it) {
    return wmget(store, it) || {};
  };
  has = function (it) {
    return wmhas(store, it);
  };
} else {
  var STATE = sharedKey('state');
  hiddenKeys[STATE] = true;
  set = function (it, metadata) {
    if (hasOwn(it, STATE)) throw new TypeError(OBJECT_ALREADY_INITIALIZED);
    metadata.facade = it;
    createNonEnumerableProperty(it, STATE, metadata);
    return metadata;
  };
  get = function (it) {
    return hasOwn(it, STATE) ? it[STATE] : {};
  };
  has = function (it) {
    return hasOwn(it, STATE);
  };
}

module.exports = {
  set: set,
  get: get,
  has: has,
  enforce: enforce,
  getterFor: getterFor
};


/***/ }),

/***/ 47909:
/*!********************************************************************!*\
  !*** ./node_modules/core-js/internals/is-array-iterator-method.js ***!
  \********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);
var Iterators = __webpack_require__(/*! ../internals/iterators */ 88424);

var ITERATOR = wellKnownSymbol('iterator');
var ArrayPrototype = Array.prototype;

// check on default Array iterator
module.exports = function (it) {
  return it !== undefined && (Iterators.Array === it || ArrayPrototype[ITERATOR] === it);
};


/***/ }),

/***/ 48143:
/*!****************************************************!*\
  !*** ./node_modules/core-js/internals/is-array.js ***!
  \****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var classof = __webpack_require__(/*! ../internals/classof-raw */ 91401);

// `IsArray` abstract operation
// https://tc39.es/ecma262/#sec-isarray
// eslint-disable-next-line es/no-array-isarray -- safe
module.exports = Array.isArray || function isArray(argument) {
  return classof(argument) == 'Array';
};


/***/ }),

/***/ 74513:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/is-callable.js ***!
  \*******************************************************/
/***/ ((module) => {

// `IsCallable` abstract operation
// https://tc39.es/ecma262/#sec-iscallable
module.exports = function (argument) {
  return typeof argument == 'function';
};


/***/ }),

/***/ 45200:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/is-constructor.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var classof = __webpack_require__(/*! ../internals/classof */ 2212);
var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var inspectSource = __webpack_require__(/*! ../internals/inspect-source */ 56073);

var noop = function () { /* empty */ };
var empty = [];
var construct = getBuiltIn('Reflect', 'construct');
var constructorRegExp = /^\s*(?:class|function)\b/;
var exec = uncurryThis(constructorRegExp.exec);
var INCORRECT_TO_STRING = !constructorRegExp.exec(noop);

var isConstructorModern = function isConstructor(argument) {
  if (!isCallable(argument)) return false;
  try {
    construct(noop, empty, argument);
    return true;
  } catch (error) {
    return false;
  }
};

var isConstructorLegacy = function isConstructor(argument) {
  if (!isCallable(argument)) return false;
  switch (classof(argument)) {
    case 'AsyncFunction':
    case 'GeneratorFunction':
    case 'AsyncGeneratorFunction': return false;
  }
  try {
    // we can't check .prototype since constructors produced by .bind haven't it
    // `Function#toString` throws on some built-it function in some legacy engines
    // (for example, `DOMQuad` and similar in FF41-)
    return INCORRECT_TO_STRING || !!exec(constructorRegExp, inspectSource(argument));
  } catch (error) {
    return true;
  }
};

isConstructorLegacy.sham = true;

// `IsConstructor` abstract operation
// https://tc39.es/ecma262/#sec-isconstructor
module.exports = !construct || fails(function () {
  var called;
  return isConstructorModern(isConstructorModern.call)
    || !isConstructorModern(Object)
    || !isConstructorModern(function () { called = true; })
    || called;
}) ? isConstructorLegacy : isConstructorModern;


/***/ }),

/***/ 89545:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/is-forced.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);

var replacement = /#|\.prototype\./;

var isForced = function (feature, detection) {
  var value = data[normalize(feature)];
  return value == POLYFILL ? true
    : value == NATIVE ? false
    : isCallable(detection) ? fails(detection)
    : !!detection;
};

var normalize = isForced.normalize = function (string) {
  return String(string).replace(replacement, '.').toLowerCase();
};

var data = isForced.data = {};
var NATIVE = isForced.NATIVE = 'N';
var POLYFILL = isForced.POLYFILL = 'P';

module.exports = isForced;


/***/ }),

/***/ 18886:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/is-object.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);

module.exports = function (it) {
  return typeof it == 'object' ? it !== null : isCallable(it);
};


/***/ }),

/***/ 42074:
/*!***************************************************!*\
  !*** ./node_modules/core-js/internals/is-pure.js ***!
  \***************************************************/
/***/ ((module) => {

module.exports = false;


/***/ }),

/***/ 92983:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/is-symbol.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var isPrototypeOf = __webpack_require__(/*! ../internals/object-is-prototype-of */ 70846);
var USE_SYMBOL_AS_UID = __webpack_require__(/*! ../internals/use-symbol-as-uid */ 56762);

var Object = global.Object;

module.exports = USE_SYMBOL_AS_UID ? function (it) {
  return typeof it == 'symbol';
} : function (it) {
  var $Symbol = getBuiltIn('Symbol');
  return isCallable($Symbol) && isPrototypeOf($Symbol.prototype, Object(it));
};


/***/ }),

/***/ 99423:
/*!***************************************************!*\
  !*** ./node_modules/core-js/internals/iterate.js ***!
  \***************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var bind = __webpack_require__(/*! ../internals/function-bind-context */ 90352);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var tryToString = __webpack_require__(/*! ../internals/try-to-string */ 67366);
var isArrayIteratorMethod = __webpack_require__(/*! ../internals/is-array-iterator-method */ 47909);
var lengthOfArrayLike = __webpack_require__(/*! ../internals/length-of-array-like */ 77951);
var isPrototypeOf = __webpack_require__(/*! ../internals/object-is-prototype-of */ 70846);
var getIterator = __webpack_require__(/*! ../internals/get-iterator */ 66029);
var getIteratorMethod = __webpack_require__(/*! ../internals/get-iterator-method */ 76644);
var iteratorClose = __webpack_require__(/*! ../internals/iterator-close */ 29838);

var TypeError = global.TypeError;

var Result = function (stopped, result) {
  this.stopped = stopped;
  this.result = result;
};

var ResultPrototype = Result.prototype;

module.exports = function (iterable, unboundFunction, options) {
  var that = options && options.that;
  var AS_ENTRIES = !!(options && options.AS_ENTRIES);
  var IS_ITERATOR = !!(options && options.IS_ITERATOR);
  var INTERRUPTED = !!(options && options.INTERRUPTED);
  var fn = bind(unboundFunction, that);
  var iterator, iterFn, index, length, result, next, step;

  var stop = function (condition) {
    if (iterator) iteratorClose(iterator, 'normal', condition);
    return new Result(true, condition);
  };

  var callFn = function (value) {
    if (AS_ENTRIES) {
      anObject(value);
      return INTERRUPTED ? fn(value[0], value[1], stop) : fn(value[0], value[1]);
    } return INTERRUPTED ? fn(value, stop) : fn(value);
  };

  if (IS_ITERATOR) {
    iterator = iterable;
  } else {
    iterFn = getIteratorMethod(iterable);
    if (!iterFn) throw TypeError(tryToString(iterable) + ' is not iterable');
    // optimisation for array iterators
    if (isArrayIteratorMethod(iterFn)) {
      for (index = 0, length = lengthOfArrayLike(iterable); length > index; index++) {
        result = callFn(iterable[index]);
        if (result && isPrototypeOf(ResultPrototype, result)) return result;
      } return new Result(false);
    }
    iterator = getIterator(iterable, iterFn);
  }

  next = iterator.next;
  while (!(step = call(next, iterator)).done) {
    try {
      result = callFn(step.value);
    } catch (error) {
      iteratorClose(iterator, 'throw', error);
    }
    if (typeof result == 'object' && result && isPrototypeOf(ResultPrototype, result)) return result;
  } return new Result(false);
};


/***/ }),

/***/ 29838:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/iterator-close.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var getMethod = __webpack_require__(/*! ../internals/get-method */ 32383);

module.exports = function (iterator, kind, value) {
  var innerResult, innerError;
  anObject(iterator);
  try {
    innerResult = getMethod(iterator, 'return');
    if (!innerResult) {
      if (kind === 'throw') throw value;
      return value;
    }
    innerResult = call(innerResult, iterator);
  } catch (error) {
    innerError = true;
    innerResult = error;
  }
  if (kind === 'throw') throw value;
  if (innerError) throw innerResult;
  anObject(innerResult);
  return value;
};


/***/ }),

/***/ 12753:
/*!**********************************************************!*\
  !*** ./node_modules/core-js/internals/iterators-core.js ***!
  \**********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var create = __webpack_require__(/*! ../internals/object-create */ 1701);
var getPrototypeOf = __webpack_require__(/*! ../internals/object-get-prototype-of */ 45456);
var redefine = __webpack_require__(/*! ../internals/redefine */ 56292);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);
var IS_PURE = __webpack_require__(/*! ../internals/is-pure */ 42074);

var ITERATOR = wellKnownSymbol('iterator');
var BUGGY_SAFARI_ITERATORS = false;

// `%IteratorPrototype%` object
// https://tc39.es/ecma262/#sec-%iteratorprototype%-object
var IteratorPrototype, PrototypeOfArrayIteratorPrototype, arrayIterator;

/* eslint-disable es/no-array-prototype-keys -- safe */
if ([].keys) {
  arrayIterator = [].keys();
  // Safari 8 has buggy iterators w/o `next`
  if (!('next' in arrayIterator)) BUGGY_SAFARI_ITERATORS = true;
  else {
    PrototypeOfArrayIteratorPrototype = getPrototypeOf(getPrototypeOf(arrayIterator));
    if (PrototypeOfArrayIteratorPrototype !== Object.prototype) IteratorPrototype = PrototypeOfArrayIteratorPrototype;
  }
}

var NEW_ITERATOR_PROTOTYPE = IteratorPrototype == undefined || fails(function () {
  var test = {};
  // FF44- legacy iterators case
  return IteratorPrototype[ITERATOR].call(test) !== test;
});

if (NEW_ITERATOR_PROTOTYPE) IteratorPrototype = {};
else if (IS_PURE) IteratorPrototype = create(IteratorPrototype);

// `%IteratorPrototype%[@@iterator]()` method
// https://tc39.es/ecma262/#sec-%iteratorprototype%-@@iterator
if (!isCallable(IteratorPrototype[ITERATOR])) {
  redefine(IteratorPrototype, ITERATOR, function () {
    return this;
  });
}

module.exports = {
  IteratorPrototype: IteratorPrototype,
  BUGGY_SAFARI_ITERATORS: BUGGY_SAFARI_ITERATORS
};


/***/ }),

/***/ 88424:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/iterators.js ***!
  \*****************************************************/
/***/ ((module) => {

module.exports = {};


/***/ }),

/***/ 77951:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/length-of-array-like.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var toLength = __webpack_require__(/*! ../internals/to-length */ 37832);

// `LengthOfArrayLike` abstract operation
// https://tc39.es/ecma262/#sec-lengthofarraylike
module.exports = function (obj) {
  return toLength(obj.length);
};


/***/ }),

/***/ 57697:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/native-symbol.js ***!
  \*********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

/* eslint-disable es/no-symbol -- required for testing */
var V8_VERSION = __webpack_require__(/*! ../internals/engine-v8-version */ 39905);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);

// eslint-disable-next-line es/no-object-getownpropertysymbols -- required for testing
module.exports = !!Object.getOwnPropertySymbols && !fails(function () {
  var symbol = Symbol();
  // Chrome 38 Symbol has incorrect toString conversion
  // `get-own-property-symbols` polyfill symbols converted to object are not Symbol instances
  return !String(symbol) || !(Object(symbol) instanceof Symbol) ||
    // Chrome 38-40 symbols are not inherited from DOM collections prototypes to instances
    !Symbol.sham && V8_VERSION && V8_VERSION < 41;
});


/***/ }),

/***/ 90898:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/native-weak-map.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var inspectSource = __webpack_require__(/*! ../internals/inspect-source */ 56073);

var WeakMap = global.WeakMap;

module.exports = isCallable(WeakMap) && /native code/.test(inspectSource(WeakMap));


/***/ }),

/***/ 1701:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/object-create.js ***!
  \*********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

/* global ActiveXObject -- old IE, WSH */
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var definePropertiesModule = __webpack_require__(/*! ../internals/object-define-properties */ 59106);
var enumBugKeys = __webpack_require__(/*! ../internals/enum-bug-keys */ 90256);
var hiddenKeys = __webpack_require__(/*! ../internals/hidden-keys */ 77086);
var html = __webpack_require__(/*! ../internals/html */ 34989);
var documentCreateElement = __webpack_require__(/*! ../internals/document-create-element */ 77783);
var sharedKey = __webpack_require__(/*! ../internals/shared-key */ 49660);

var GT = '>';
var LT = '<';
var PROTOTYPE = 'prototype';
var SCRIPT = 'script';
var IE_PROTO = sharedKey('IE_PROTO');

var EmptyConstructor = function () { /* empty */ };

var scriptTag = function (content) {
  return LT + SCRIPT + GT + content + LT + '/' + SCRIPT + GT;
};

// Create object with fake `null` prototype: use ActiveX Object with cleared prototype
var NullProtoObjectViaActiveX = function (activeXDocument) {
  activeXDocument.write(scriptTag(''));
  activeXDocument.close();
  var temp = activeXDocument.parentWindow.Object;
  activeXDocument = null; // avoid memory leak
  return temp;
};

// Create object with fake `null` prototype: use iframe Object with cleared prototype
var NullProtoObjectViaIFrame = function () {
  // Thrash, waste and sodomy: IE GC bug
  var iframe = documentCreateElement('iframe');
  var JS = 'java' + SCRIPT + ':';
  var iframeDocument;
  iframe.style.display = 'none';
  html.appendChild(iframe);
  // https://github.com/zloirock/core-js/issues/475
  iframe.src = String(JS);
  iframeDocument = iframe.contentWindow.document;
  iframeDocument.open();
  iframeDocument.write(scriptTag('document.F=Object'));
  iframeDocument.close();
  return iframeDocument.F;
};

// Check for document.domain and active x support
// No need to use active x approach when document.domain is not set
// see https://github.com/es-shims/es5-shim/issues/150
// variation of https://github.com/kitcambridge/es5-shim/commit/4f738ac066346
// avoid IE GC bug
var activeXDocument;
var NullProtoObject = function () {
  try {
    activeXDocument = new ActiveXObject('htmlfile');
  } catch (error) { /* ignore */ }
  NullProtoObject = typeof document != 'undefined'
    ? document.domain && activeXDocument
      ? NullProtoObjectViaActiveX(activeXDocument) // old IE
      : NullProtoObjectViaIFrame()
    : NullProtoObjectViaActiveX(activeXDocument); // WSH
  var length = enumBugKeys.length;
  while (length--) delete NullProtoObject[PROTOTYPE][enumBugKeys[length]];
  return NullProtoObject();
};

hiddenKeys[IE_PROTO] = true;

// `Object.create` method
// https://tc39.es/ecma262/#sec-object.create
module.exports = Object.create || function create(O, Properties) {
  var result;
  if (O !== null) {
    EmptyConstructor[PROTOTYPE] = anObject(O);
    result = new EmptyConstructor();
    EmptyConstructor[PROTOTYPE] = null;
    // add "__proto__" for Object.getPrototypeOf polyfill
    result[IE_PROTO] = O;
  } else result = NullProtoObject();
  return Properties === undefined ? result : definePropertiesModule.f(result, Properties);
};


/***/ }),

/***/ 59106:
/*!********************************************************************!*\
  !*** ./node_modules/core-js/internals/object-define-properties.js ***!
  \********************************************************************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var V8_PROTOTYPE_DEFINE_BUG = __webpack_require__(/*! ../internals/v8-prototype-define-bug */ 50401);
var definePropertyModule = __webpack_require__(/*! ../internals/object-define-property */ 76607);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var toIndexedObject = __webpack_require__(/*! ../internals/to-indexed-object */ 88436);
var objectKeys = __webpack_require__(/*! ../internals/object-keys */ 75159);

// `Object.defineProperties` method
// https://tc39.es/ecma262/#sec-object.defineproperties
// eslint-disable-next-line es/no-object-defineproperties -- safe
exports.f = DESCRIPTORS && !V8_PROTOTYPE_DEFINE_BUG ? Object.defineProperties : function defineProperties(O, Properties) {
  anObject(O);
  var props = toIndexedObject(Properties);
  var keys = objectKeys(Properties);
  var length = keys.length;
  var index = 0;
  var key;
  while (length > index) definePropertyModule.f(O, key = keys[index++], props[key]);
  return O;
};


/***/ }),

/***/ 76607:
/*!******************************************************************!*\
  !*** ./node_modules/core-js/internals/object-define-property.js ***!
  \******************************************************************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var IE8_DOM_DEFINE = __webpack_require__(/*! ../internals/ie8-dom-define */ 5873);
var V8_PROTOTYPE_DEFINE_BUG = __webpack_require__(/*! ../internals/v8-prototype-define-bug */ 50401);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var toPropertyKey = __webpack_require__(/*! ../internals/to-property-key */ 12637);

var TypeError = global.TypeError;
// eslint-disable-next-line es/no-object-defineproperty -- safe
var $defineProperty = Object.defineProperty;
// eslint-disable-next-line es/no-object-getownpropertydescriptor -- safe
var $getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;
var ENUMERABLE = 'enumerable';
var CONFIGURABLE = 'configurable';
var WRITABLE = 'writable';

// `Object.defineProperty` method
// https://tc39.es/ecma262/#sec-object.defineproperty
exports.f = DESCRIPTORS ? V8_PROTOTYPE_DEFINE_BUG ? function defineProperty(O, P, Attributes) {
  anObject(O);
  P = toPropertyKey(P);
  anObject(Attributes);
  if (typeof O === 'function' && P === 'prototype' && 'value' in Attributes && WRITABLE in Attributes && !Attributes[WRITABLE]) {
    var current = $getOwnPropertyDescriptor(O, P);
    if (current && current[WRITABLE]) {
      O[P] = Attributes.value;
      Attributes = {
        configurable: CONFIGURABLE in Attributes ? Attributes[CONFIGURABLE] : current[CONFIGURABLE],
        enumerable: ENUMERABLE in Attributes ? Attributes[ENUMERABLE] : current[ENUMERABLE],
        writable: false
      };
    }
  } return $defineProperty(O, P, Attributes);
} : $defineProperty : function defineProperty(O, P, Attributes) {
  anObject(O);
  P = toPropertyKey(P);
  anObject(Attributes);
  if (IE8_DOM_DEFINE) try {
    return $defineProperty(O, P, Attributes);
  } catch (error) { /* empty */ }
  if ('get' in Attributes || 'set' in Attributes) throw TypeError('Accessors not supported');
  if ('value' in Attributes) O[P] = Attributes.value;
  return O;
};


/***/ }),

/***/ 50313:
/*!******************************************************************************!*\
  !*** ./node_modules/core-js/internals/object-get-own-property-descriptor.js ***!
  \******************************************************************************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var propertyIsEnumerableModule = __webpack_require__(/*! ../internals/object-property-is-enumerable */ 56491);
var createPropertyDescriptor = __webpack_require__(/*! ../internals/create-property-descriptor */ 8808);
var toIndexedObject = __webpack_require__(/*! ../internals/to-indexed-object */ 88436);
var toPropertyKey = __webpack_require__(/*! ../internals/to-property-key */ 12637);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var IE8_DOM_DEFINE = __webpack_require__(/*! ../internals/ie8-dom-define */ 5873);

// eslint-disable-next-line es/no-object-getownpropertydescriptor -- safe
var $getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;

// `Object.getOwnPropertyDescriptor` method
// https://tc39.es/ecma262/#sec-object.getownpropertydescriptor
exports.f = DESCRIPTORS ? $getOwnPropertyDescriptor : function getOwnPropertyDescriptor(O, P) {
  O = toIndexedObject(O);
  P = toPropertyKey(P);
  if (IE8_DOM_DEFINE) try {
    return $getOwnPropertyDescriptor(O, P);
  } catch (error) { /* empty */ }
  if (hasOwn(O, P)) return createPropertyDescriptor(!call(propertyIsEnumerableModule.f, O, P), O[P]);
};


/***/ }),

/***/ 53138:
/*!**********************************************************************************!*\
  !*** ./node_modules/core-js/internals/object-get-own-property-names-external.js ***!
  \**********************************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

/* eslint-disable es/no-object-getownpropertynames -- safe */
var classof = __webpack_require__(/*! ../internals/classof-raw */ 91401);
var toIndexedObject = __webpack_require__(/*! ../internals/to-indexed-object */ 88436);
var $getOwnPropertyNames = (__webpack_require__(/*! ../internals/object-get-own-property-names */ 24283).f);
var arraySlice = __webpack_require__(/*! ../internals/array-slice-simple */ 45005);

var windowNames = typeof window == 'object' && window && Object.getOwnPropertyNames
  ? Object.getOwnPropertyNames(window) : [];

var getWindowNames = function (it) {
  try {
    return $getOwnPropertyNames(it);
  } catch (error) {
    return arraySlice(windowNames);
  }
};

// fallback for IE11 buggy Object.getOwnPropertyNames with iframe and window
module.exports.f = function getOwnPropertyNames(it) {
  return windowNames && classof(it) == 'Window'
    ? getWindowNames(it)
    : $getOwnPropertyNames(toIndexedObject(it));
};


/***/ }),

/***/ 24283:
/*!*************************************************************************!*\
  !*** ./node_modules/core-js/internals/object-get-own-property-names.js ***!
  \*************************************************************************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

var internalObjectKeys = __webpack_require__(/*! ../internals/object-keys-internal */ 73187);
var enumBugKeys = __webpack_require__(/*! ../internals/enum-bug-keys */ 90256);

var hiddenKeys = enumBugKeys.concat('length', 'prototype');

// `Object.getOwnPropertyNames` method
// https://tc39.es/ecma262/#sec-object.getownpropertynames
// eslint-disable-next-line es/no-object-getownpropertynames -- safe
exports.f = Object.getOwnPropertyNames || function getOwnPropertyNames(O) {
  return internalObjectKeys(O, hiddenKeys);
};


/***/ }),

/***/ 86095:
/*!***************************************************************************!*\
  !*** ./node_modules/core-js/internals/object-get-own-property-symbols.js ***!
  \***************************************************************************/
/***/ ((__unused_webpack_module, exports) => {

// eslint-disable-next-line es/no-object-getownpropertysymbols -- safe
exports.f = Object.getOwnPropertySymbols;


/***/ }),

/***/ 45456:
/*!*******************************************************************!*\
  !*** ./node_modules/core-js/internals/object-get-prototype-of.js ***!
  \*******************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var toObject = __webpack_require__(/*! ../internals/to-object */ 73784);
var sharedKey = __webpack_require__(/*! ../internals/shared-key */ 49660);
var CORRECT_PROTOTYPE_GETTER = __webpack_require__(/*! ../internals/correct-prototype-getter */ 68745);

var IE_PROTO = sharedKey('IE_PROTO');
var Object = global.Object;
var ObjectPrototype = Object.prototype;

// `Object.getPrototypeOf` method
// https://tc39.es/ecma262/#sec-object.getprototypeof
module.exports = CORRECT_PROTOTYPE_GETTER ? Object.getPrototypeOf : function (O) {
  var object = toObject(O);
  if (hasOwn(object, IE_PROTO)) return object[IE_PROTO];
  var constructor = object.constructor;
  if (isCallable(constructor) && object instanceof constructor) {
    return constructor.prototype;
  } return object instanceof Object ? ObjectPrototype : null;
};


/***/ }),

/***/ 53892:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/object-is-extensible.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var fails = __webpack_require__(/*! ../internals/fails */ 27944);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var classof = __webpack_require__(/*! ../internals/classof-raw */ 91401);
var ARRAY_BUFFER_NON_EXTENSIBLE = __webpack_require__(/*! ../internals/array-buffer-non-extensible */ 73588);

// eslint-disable-next-line es/no-object-isextensible -- safe
var $isExtensible = Object.isExtensible;
var FAILS_ON_PRIMITIVES = fails(function () { $isExtensible(1); });

// `Object.isExtensible` method
// https://tc39.es/ecma262/#sec-object.isextensible
module.exports = (FAILS_ON_PRIMITIVES || ARRAY_BUFFER_NON_EXTENSIBLE) ? function isExtensible(it) {
  if (!isObject(it)) return false;
  if (ARRAY_BUFFER_NON_EXTENSIBLE && classof(it) == 'ArrayBuffer') return false;
  return $isExtensible ? $isExtensible(it) : true;
} : $isExtensible;


/***/ }),

/***/ 70846:
/*!******************************************************************!*\
  !*** ./node_modules/core-js/internals/object-is-prototype-of.js ***!
  \******************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);

module.exports = uncurryThis({}.isPrototypeOf);


/***/ }),

/***/ 73187:
/*!****************************************************************!*\
  !*** ./node_modules/core-js/internals/object-keys-internal.js ***!
  \****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var toIndexedObject = __webpack_require__(/*! ../internals/to-indexed-object */ 88436);
var indexOf = (__webpack_require__(/*! ../internals/array-includes */ 34273).indexOf);
var hiddenKeys = __webpack_require__(/*! ../internals/hidden-keys */ 77086);

var push = uncurryThis([].push);

module.exports = function (object, names) {
  var O = toIndexedObject(object);
  var i = 0;
  var result = [];
  var key;
  for (key in O) !hasOwn(hiddenKeys, key) && hasOwn(O, key) && push(result, key);
  // Don't enum bug & hidden keys
  while (names.length > i) if (hasOwn(O, key = names[i++])) {
    ~indexOf(result, key) || push(result, key);
  }
  return result;
};


/***/ }),

/***/ 75159:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/object-keys.js ***!
  \*******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var internalObjectKeys = __webpack_require__(/*! ../internals/object-keys-internal */ 73187);
var enumBugKeys = __webpack_require__(/*! ../internals/enum-bug-keys */ 90256);

// `Object.keys` method
// https://tc39.es/ecma262/#sec-object.keys
// eslint-disable-next-line es/no-object-keys -- safe
module.exports = Object.keys || function keys(O) {
  return internalObjectKeys(O, enumBugKeys);
};


/***/ }),

/***/ 56491:
/*!*************************************************************************!*\
  !*** ./node_modules/core-js/internals/object-property-is-enumerable.js ***!
  \*************************************************************************/
/***/ ((__unused_webpack_module, exports) => {

"use strict";

var $propertyIsEnumerable = {}.propertyIsEnumerable;
// eslint-disable-next-line es/no-object-getownpropertydescriptor -- safe
var getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;

// Nashorn ~ JDK8 bug
var NASHORN_BUG = getOwnPropertyDescriptor && !$propertyIsEnumerable.call({ 1: 2 }, 1);

// `Object.prototype.propertyIsEnumerable` method implementation
// https://tc39.es/ecma262/#sec-object.prototype.propertyisenumerable
exports.f = NASHORN_BUG ? function propertyIsEnumerable(V) {
  var descriptor = getOwnPropertyDescriptor(this, V);
  return !!descriptor && descriptor.enumerable;
} : $propertyIsEnumerable;


/***/ }),

/***/ 86736:
/*!*******************************************************************!*\
  !*** ./node_modules/core-js/internals/object-set-prototype-of.js ***!
  \*******************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

/* eslint-disable no-proto -- safe */
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var aPossiblePrototype = __webpack_require__(/*! ../internals/a-possible-prototype */ 8254);

// `Object.setPrototypeOf` method
// https://tc39.es/ecma262/#sec-object.setprototypeof
// Works with __proto__ only. Old v8 can't work with null proto objects.
// eslint-disable-next-line es/no-object-setprototypeof -- safe
module.exports = Object.setPrototypeOf || ('__proto__' in {} ? function () {
  var CORRECT_SETTER = false;
  var test = {};
  var setter;
  try {
    // eslint-disable-next-line es/no-object-getownpropertydescriptor -- safe
    setter = uncurryThis(Object.getOwnPropertyDescriptor(Object.prototype, '__proto__').set);
    setter(test, []);
    CORRECT_SETTER = test instanceof Array;
  } catch (error) { /* empty */ }
  return function setPrototypeOf(O, proto) {
    anObject(O);
    aPossiblePrototype(proto);
    if (CORRECT_SETTER) setter(O, proto);
    else O.__proto__ = proto;
    return O;
  };
}() : undefined);


/***/ }),

/***/ 3409:
/*!*****************************************************************!*\
  !*** ./node_modules/core-js/internals/ordinary-to-primitive.js ***!
  \*****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);

var TypeError = global.TypeError;

// `OrdinaryToPrimitive` abstract operation
// https://tc39.es/ecma262/#sec-ordinarytoprimitive
module.exports = function (input, pref) {
  var fn, val;
  if (pref === 'string' && isCallable(fn = input.toString) && !isObject(val = call(fn, input))) return val;
  if (isCallable(fn = input.valueOf) && !isObject(val = call(fn, input))) return val;
  if (pref !== 'string' && isCallable(fn = input.toString) && !isObject(val = call(fn, input))) return val;
  throw TypeError("Can't convert object to primitive value");
};


/***/ }),

/***/ 24432:
/*!****************************************************!*\
  !*** ./node_modules/core-js/internals/own-keys.js ***!
  \****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var getOwnPropertyNamesModule = __webpack_require__(/*! ../internals/object-get-own-property-names */ 24283);
var getOwnPropertySymbolsModule = __webpack_require__(/*! ../internals/object-get-own-property-symbols */ 86095);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var concat = uncurryThis([].concat);

// all object keys, includes non-enumerable and symbols
module.exports = getBuiltIn('Reflect', 'ownKeys') || function ownKeys(it) {
  var keys = getOwnPropertyNamesModule.f(anObject(it));
  var getOwnPropertySymbols = getOwnPropertySymbolsModule.f;
  return getOwnPropertySymbols ? concat(keys, getOwnPropertySymbols(it)) : keys;
};


/***/ }),

/***/ 30924:
/*!********************************************************!*\
  !*** ./node_modules/core-js/internals/redefine-all.js ***!
  \********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var redefine = __webpack_require__(/*! ../internals/redefine */ 56292);

module.exports = function (target, src, options) {
  for (var key in src) redefine(target, key, src[key], options);
  return target;
};


/***/ }),

/***/ 56292:
/*!****************************************************!*\
  !*** ./node_modules/core-js/internals/redefine.js ***!
  \****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var isCallable = __webpack_require__(/*! ../internals/is-callable */ 74513);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var createNonEnumerableProperty = __webpack_require__(/*! ../internals/create-non-enumerable-property */ 33779);
var setGlobal = __webpack_require__(/*! ../internals/set-global */ 85123);
var inspectSource = __webpack_require__(/*! ../internals/inspect-source */ 56073);
var InternalStateModule = __webpack_require__(/*! ../internals/internal-state */ 90979);
var CONFIGURABLE_FUNCTION_NAME = (__webpack_require__(/*! ../internals/function-name */ 4513).CONFIGURABLE);

var getInternalState = InternalStateModule.get;
var enforceInternalState = InternalStateModule.enforce;
var TEMPLATE = String(String).split('String');

(module.exports = function (O, key, value, options) {
  var unsafe = options ? !!options.unsafe : false;
  var simple = options ? !!options.enumerable : false;
  var noTargetGet = options ? !!options.noTargetGet : false;
  var name = options && options.name !== undefined ? options.name : key;
  var state;
  if (isCallable(value)) {
    if (String(name).slice(0, 7) === 'Symbol(') {
      name = '[' + String(name).replace(/^Symbol\(([^)]*)\)/, '$1') + ']';
    }
    if (!hasOwn(value, 'name') || (CONFIGURABLE_FUNCTION_NAME && value.name !== name)) {
      createNonEnumerableProperty(value, 'name', name);
    }
    state = enforceInternalState(value);
    if (!state.source) {
      state.source = TEMPLATE.join(typeof name == 'string' ? name : '');
    }
  }
  if (O === global) {
    if (simple) O[key] = value;
    else setGlobal(key, value);
    return;
  } else if (!unsafe) {
    delete O[key];
  } else if (!noTargetGet && O[key]) {
    simple = true;
  }
  if (simple) O[key] = value;
  else createNonEnumerableProperty(O, key, value);
// add fake Function#toString for correct work wrapped methods / constructors with methods like LoDash isNative
})(Function.prototype, 'toString', function toString() {
  return isCallable(this) && getInternalState(this).source || inspectSource(this);
});


/***/ }),

/***/ 88078:
/*!************************************************************!*\
  !*** ./node_modules/core-js/internals/reflect-metadata.js ***!
  \************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

// TODO: in core-js@4, move /modules/ dependencies to public entries for better optimization by tools like `preset-env`
__webpack_require__(/*! ../modules/es.map */ 74564);
__webpack_require__(/*! ../modules/es.weak-map */ 63902);
var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var shared = __webpack_require__(/*! ../internals/shared */ 15443);

var Map = getBuiltIn('Map');
var WeakMap = getBuiltIn('WeakMap');
var push = uncurryThis([].push);

var metadata = shared('metadata');
var store = metadata.store || (metadata.store = new WeakMap());

var getOrCreateMetadataMap = function (target, targetKey, create) {
  var targetMetadata = store.get(target);
  if (!targetMetadata) {
    if (!create) return;
    store.set(target, targetMetadata = new Map());
  }
  var keyMetadata = targetMetadata.get(targetKey);
  if (!keyMetadata) {
    if (!create) return;
    targetMetadata.set(targetKey, keyMetadata = new Map());
  } return keyMetadata;
};

var ordinaryHasOwnMetadata = function (MetadataKey, O, P) {
  var metadataMap = getOrCreateMetadataMap(O, P, false);
  return metadataMap === undefined ? false : metadataMap.has(MetadataKey);
};

var ordinaryGetOwnMetadata = function (MetadataKey, O, P) {
  var metadataMap = getOrCreateMetadataMap(O, P, false);
  return metadataMap === undefined ? undefined : metadataMap.get(MetadataKey);
};

var ordinaryDefineOwnMetadata = function (MetadataKey, MetadataValue, O, P) {
  getOrCreateMetadataMap(O, P, true).set(MetadataKey, MetadataValue);
};

var ordinaryOwnMetadataKeys = function (target, targetKey) {
  var metadataMap = getOrCreateMetadataMap(target, targetKey, false);
  var keys = [];
  if (metadataMap) metadataMap.forEach(function (_, key) { push(keys, key); });
  return keys;
};

var toMetadataKey = function (it) {
  return it === undefined || typeof it == 'symbol' ? it : String(it);
};

module.exports = {
  store: store,
  getMap: getOrCreateMetadataMap,
  has: ordinaryHasOwnMetadata,
  get: ordinaryGetOwnMetadata,
  set: ordinaryDefineOwnMetadata,
  keys: ordinaryOwnMetadataKeys,
  toKey: toMetadataKey
};


/***/ }),

/***/ 21413:
/*!********************************************************************!*\
  !*** ./node_modules/core-js/internals/require-object-coercible.js ***!
  \********************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);

var TypeError = global.TypeError;

// `RequireObjectCoercible` abstract operation
// https://tc39.es/ecma262/#sec-requireobjectcoercible
module.exports = function (it) {
  if (it == undefined) throw TypeError("Can't call method on " + it);
  return it;
};


/***/ }),

/***/ 85123:
/*!******************************************************!*\
  !*** ./node_modules/core-js/internals/set-global.js ***!
  \******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);

// eslint-disable-next-line es/no-object-defineproperty -- safe
var defineProperty = Object.defineProperty;

module.exports = function (key, value) {
  try {
    defineProperty(global, key, { value: value, configurable: true, writable: true });
  } catch (error) {
    global[key] = value;
  } return value;
};


/***/ }),

/***/ 84087:
/*!*******************************************************!*\
  !*** ./node_modules/core-js/internals/set-species.js ***!
  \*******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var getBuiltIn = __webpack_require__(/*! ../internals/get-built-in */ 2149);
var definePropertyModule = __webpack_require__(/*! ../internals/object-define-property */ 76607);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);
var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);

var SPECIES = wellKnownSymbol('species');

module.exports = function (CONSTRUCTOR_NAME) {
  var Constructor = getBuiltIn(CONSTRUCTOR_NAME);
  var defineProperty = definePropertyModule.f;

  if (DESCRIPTORS && Constructor && !Constructor[SPECIES]) {
    defineProperty(Constructor, SPECIES, {
      configurable: true,
      get: function () { return this; }
    });
  }
};


/***/ }),

/***/ 15387:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/set-to-string-tag.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var defineProperty = (__webpack_require__(/*! ../internals/object-define-property */ 76607).f);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var TO_STRING_TAG = wellKnownSymbol('toStringTag');

module.exports = function (target, TAG, STATIC) {
  if (target && !STATIC) target = target.prototype;
  if (target && !hasOwn(target, TO_STRING_TAG)) {
    defineProperty(target, TO_STRING_TAG, { configurable: true, value: TAG });
  }
};


/***/ }),

/***/ 49660:
/*!******************************************************!*\
  !*** ./node_modules/core-js/internals/shared-key.js ***!
  \******************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var shared = __webpack_require__(/*! ../internals/shared */ 15443);
var uid = __webpack_require__(/*! ../internals/uid */ 1387);

var keys = shared('keys');

module.exports = function (key) {
  return keys[key] || (keys[key] = uid(key));
};


/***/ }),

/***/ 35691:
/*!********************************************************!*\
  !*** ./node_modules/core-js/internals/shared-store.js ***!
  \********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var setGlobal = __webpack_require__(/*! ../internals/set-global */ 85123);

var SHARED = '__core-js_shared__';
var store = global[SHARED] || setGlobal(SHARED, {});

module.exports = store;


/***/ }),

/***/ 15443:
/*!**************************************************!*\
  !*** ./node_modules/core-js/internals/shared.js ***!
  \**************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var IS_PURE = __webpack_require__(/*! ../internals/is-pure */ 42074);
var store = __webpack_require__(/*! ../internals/shared-store */ 35691);

(module.exports = function (key, value) {
  return store[key] || (store[key] = value !== undefined ? value : {});
})('versions', []).push({
  version: '3.20.3',
  mode: IS_PURE ? 'pure' : 'global',
  copyright: '© 2014-2022 Denis Pushkarev (zloirock.ru)',
  license: 'https://github.com/zloirock/core-js/blob/v3.20.3/LICENSE',
  source: 'https://github.com/zloirock/core-js'
});


/***/ }),

/***/ 8064:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/to-absolute-index.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var toIntegerOrInfinity = __webpack_require__(/*! ../internals/to-integer-or-infinity */ 32048);

var max = Math.max;
var min = Math.min;

// Helper for a popular repeating case of the spec:
// Let integer be ? ToInteger(index).
// If integer < 0, let result be max((length + integer), 0); else let result be min(integer, length).
module.exports = function (index, length) {
  var integer = toIntegerOrInfinity(index);
  return integer < 0 ? max(integer + length, 0) : min(integer, length);
};


/***/ }),

/***/ 88436:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/to-indexed-object.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

// toObject with fallback for non-array-like ES3 strings
var IndexedObject = __webpack_require__(/*! ../internals/indexed-object */ 32462);
var requireObjectCoercible = __webpack_require__(/*! ../internals/require-object-coercible */ 21413);

module.exports = function (it) {
  return IndexedObject(requireObjectCoercible(it));
};


/***/ }),

/***/ 32048:
/*!******************************************************************!*\
  !*** ./node_modules/core-js/internals/to-integer-or-infinity.js ***!
  \******************************************************************/
/***/ ((module) => {

var ceil = Math.ceil;
var floor = Math.floor;

// `ToIntegerOrInfinity` abstract operation
// https://tc39.es/ecma262/#sec-tointegerorinfinity
module.exports = function (argument) {
  var number = +argument;
  // eslint-disable-next-line no-self-compare -- safe
  return number !== number || number === 0 ? 0 : (number > 0 ? floor : ceil)(number);
};


/***/ }),

/***/ 37832:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/to-length.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var toIntegerOrInfinity = __webpack_require__(/*! ../internals/to-integer-or-infinity */ 32048);

var min = Math.min;

// `ToLength` abstract operation
// https://tc39.es/ecma262/#sec-tolength
module.exports = function (argument) {
  return argument > 0 ? min(toIntegerOrInfinity(argument), 0x1FFFFFFFFFFFFF) : 0; // 2 ** 53 - 1 == 9007199254740991
};


/***/ }),

/***/ 73784:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/internals/to-object.js ***!
  \*****************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var requireObjectCoercible = __webpack_require__(/*! ../internals/require-object-coercible */ 21413);

var Object = global.Object;

// `ToObject` abstract operation
// https://tc39.es/ecma262/#sec-toobject
module.exports = function (argument) {
  return Object(requireObjectCoercible(argument));
};


/***/ }),

/***/ 26281:
/*!********************************************************!*\
  !*** ./node_modules/core-js/internals/to-primitive.js ***!
  \********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var call = __webpack_require__(/*! ../internals/function-call */ 39836);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var isSymbol = __webpack_require__(/*! ../internals/is-symbol */ 92983);
var getMethod = __webpack_require__(/*! ../internals/get-method */ 32383);
var ordinaryToPrimitive = __webpack_require__(/*! ../internals/ordinary-to-primitive */ 3409);
var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var TypeError = global.TypeError;
var TO_PRIMITIVE = wellKnownSymbol('toPrimitive');

// `ToPrimitive` abstract operation
// https://tc39.es/ecma262/#sec-toprimitive
module.exports = function (input, pref) {
  if (!isObject(input) || isSymbol(input)) return input;
  var exoticToPrim = getMethod(input, TO_PRIMITIVE);
  var result;
  if (exoticToPrim) {
    if (pref === undefined) pref = 'default';
    result = call(exoticToPrim, input, pref);
    if (!isObject(result) || isSymbol(result)) return result;
    throw TypeError("Can't convert object to primitive value");
  }
  if (pref === undefined) pref = 'number';
  return ordinaryToPrimitive(input, pref);
};


/***/ }),

/***/ 12637:
/*!***********************************************************!*\
  !*** ./node_modules/core-js/internals/to-property-key.js ***!
  \***********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var toPrimitive = __webpack_require__(/*! ../internals/to-primitive */ 26281);
var isSymbol = __webpack_require__(/*! ../internals/is-symbol */ 92983);

// `ToPropertyKey` abstract operation
// https://tc39.es/ecma262/#sec-topropertykey
module.exports = function (argument) {
  var key = toPrimitive(argument, 'string');
  return isSymbol(key) ? key : key + '';
};


/***/ }),

/***/ 85706:
/*!*****************************************************************!*\
  !*** ./node_modules/core-js/internals/to-string-tag-support.js ***!
  \*****************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var wellKnownSymbol = __webpack_require__(/*! ../internals/well-known-symbol */ 47014);

var TO_STRING_TAG = wellKnownSymbol('toStringTag');
var test = {};

test[TO_STRING_TAG] = 'z';

module.exports = String(test) === '[object z]';


/***/ }),

/***/ 67366:
/*!*********************************************************!*\
  !*** ./node_modules/core-js/internals/try-to-string.js ***!
  \*********************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);

var String = global.String;

module.exports = function (argument) {
  try {
    return String(argument);
  } catch (error) {
    return 'Object';
  }
};


/***/ }),

/***/ 1387:
/*!***********************************************!*\
  !*** ./node_modules/core-js/internals/uid.js ***!
  \***********************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);

var id = 0;
var postfix = Math.random();
var toString = uncurryThis(1.0.toString);

module.exports = function (key) {
  return 'Symbol(' + (key === undefined ? '' : key) + ')_' + toString(++id + postfix, 36);
};


/***/ }),

/***/ 56762:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/use-symbol-as-uid.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

/* eslint-disable es/no-symbol -- required for testing */
var NATIVE_SYMBOL = __webpack_require__(/*! ../internals/native-symbol */ 57697);

module.exports = NATIVE_SYMBOL
  && !Symbol.sham
  && typeof Symbol.iterator == 'symbol';


/***/ }),

/***/ 50401:
/*!*******************************************************************!*\
  !*** ./node_modules/core-js/internals/v8-prototype-define-bug.js ***!
  \*******************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var DESCRIPTORS = __webpack_require__(/*! ../internals/descriptors */ 45601);
var fails = __webpack_require__(/*! ../internals/fails */ 27944);

// V8 ~ Chrome 36-
// https://bugs.chromium.org/p/v8/issues/detail?id=3334
module.exports = DESCRIPTORS && fails(function () {
  // eslint-disable-next-line es/no-object-defineproperty -- required for testing
  return Object.defineProperty(function () { /* empty */ }, 'prototype', {
    value: 42,
    writable: false
  }).prototype != 42;
});


/***/ }),

/***/ 47014:
/*!*************************************************************!*\
  !*** ./node_modules/core-js/internals/well-known-symbol.js ***!
  \*************************************************************/
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

var global = __webpack_require__(/*! ../internals/global */ 94720);
var shared = __webpack_require__(/*! ../internals/shared */ 15443);
var hasOwn = __webpack_require__(/*! ../internals/has-own-property */ 8578);
var uid = __webpack_require__(/*! ../internals/uid */ 1387);
var NATIVE_SYMBOL = __webpack_require__(/*! ../internals/native-symbol */ 57697);
var USE_SYMBOL_AS_UID = __webpack_require__(/*! ../internals/use-symbol-as-uid */ 56762);

var WellKnownSymbolsStore = shared('wks');
var Symbol = global.Symbol;
var symbolFor = Symbol && Symbol['for'];
var createWellKnownSymbol = USE_SYMBOL_AS_UID ? Symbol : Symbol && Symbol.withoutSetter || uid;

module.exports = function (name) {
  if (!hasOwn(WellKnownSymbolsStore, name) || !(NATIVE_SYMBOL || typeof WellKnownSymbolsStore[name] == 'string')) {
    var description = 'Symbol.' + name;
    if (NATIVE_SYMBOL && hasOwn(Symbol, name)) {
      WellKnownSymbolsStore[name] = Symbol[name];
    } else if (USE_SYMBOL_AS_UID && symbolFor) {
      WellKnownSymbolsStore[name] = symbolFor(description);
    } else {
      WellKnownSymbolsStore[name] = createWellKnownSymbol(description);
    }
  } return WellKnownSymbolsStore[name];
};


/***/ }),

/***/ 74564:
/*!************************************************!*\
  !*** ./node_modules/core-js/modules/es.map.js ***!
  \************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var collection = __webpack_require__(/*! ../internals/collection */ 87126);
var collectionStrong = __webpack_require__(/*! ../internals/collection-strong */ 43977);

// `Map` constructor
// https://tc39.es/ecma262/#sec-map-objects
collection('Map', function (init) {
  return function Map() { return init(this, arguments.length ? arguments[0] : undefined); };
}, collectionStrong);


/***/ }),

/***/ 63902:
/*!*****************************************************!*\
  !*** ./node_modules/core-js/modules/es.weak-map.js ***!
  \*****************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

"use strict";

var global = __webpack_require__(/*! ../internals/global */ 94720);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var redefineAll = __webpack_require__(/*! ../internals/redefine-all */ 30924);
var InternalMetadataModule = __webpack_require__(/*! ../internals/internal-metadata */ 19346);
var collection = __webpack_require__(/*! ../internals/collection */ 87126);
var collectionWeak = __webpack_require__(/*! ../internals/collection-weak */ 61725);
var isObject = __webpack_require__(/*! ../internals/is-object */ 18886);
var isExtensible = __webpack_require__(/*! ../internals/object-is-extensible */ 53892);
var enforceInternalState = (__webpack_require__(/*! ../internals/internal-state */ 90979).enforce);
var NATIVE_WEAK_MAP = __webpack_require__(/*! ../internals/native-weak-map */ 90898);

var IS_IE11 = !global.ActiveXObject && 'ActiveXObject' in global;
var InternalWeakMap;

var wrapper = function (init) {
  return function WeakMap() {
    return init(this, arguments.length ? arguments[0] : undefined);
  };
};

// `WeakMap` constructor
// https://tc39.es/ecma262/#sec-weakmap-constructor
var $WeakMap = collection('WeakMap', wrapper, collectionWeak);

// IE11 WeakMap frozen keys fix
// We can't use feature detection because it crash some old IE builds
// https://github.com/zloirock/core-js/issues/485
if (NATIVE_WEAK_MAP && IS_IE11) {
  InternalWeakMap = collectionWeak.getConstructor(wrapper, 'WeakMap', true);
  InternalMetadataModule.enable();
  var WeakMapPrototype = $WeakMap.prototype;
  var nativeDelete = uncurryThis(WeakMapPrototype['delete']);
  var nativeHas = uncurryThis(WeakMapPrototype.has);
  var nativeGet = uncurryThis(WeakMapPrototype.get);
  var nativeSet = uncurryThis(WeakMapPrototype.set);
  redefineAll(WeakMapPrototype, {
    'delete': function (key) {
      if (isObject(key) && !isExtensible(key)) {
        var state = enforceInternalState(this);
        if (!state.frozen) state.frozen = new InternalWeakMap();
        return nativeDelete(this, key) || state.frozen['delete'](key);
      } return nativeDelete(this, key);
    },
    has: function has(key) {
      if (isObject(key) && !isExtensible(key)) {
        var state = enforceInternalState(this);
        if (!state.frozen) state.frozen = new InternalWeakMap();
        return nativeHas(this, key) || state.frozen.has(key);
      } return nativeHas(this, key);
    },
    get: function get(key) {
      if (isObject(key) && !isExtensible(key)) {
        var state = enforceInternalState(this);
        if (!state.frozen) state.frozen = new InternalWeakMap();
        return nativeHas(this, key) ? nativeGet(this, key) : state.frozen.get(key);
      } return nativeGet(this, key);
    },
    set: function set(key, value) {
      if (isObject(key) && !isExtensible(key)) {
        var state = enforceInternalState(this);
        if (!state.frozen) state.frozen = new InternalWeakMap();
        nativeHas(this, key) ? nativeSet(this, key, value) : state.frozen.set(key, value);
      } else nativeSet(this, key, value);
      return this;
    }
  });
}


/***/ }),

/***/ 95482:
/*!************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.define-metadata.js ***!
  \************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var toMetadataKey = ReflectMetadataModule.toKey;
var ordinaryDefineOwnMetadata = ReflectMetadataModule.set;

// `Reflect.defineMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  defineMetadata: function defineMetadata(metadataKey, metadataValue, target /* , targetKey */) {
    var targetKey = arguments.length < 4 ? undefined : toMetadataKey(arguments[3]);
    ordinaryDefineOwnMetadata(metadataKey, metadataValue, anObject(target), targetKey);
  }
});


/***/ }),

/***/ 94701:
/*!************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.delete-metadata.js ***!
  \************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var toMetadataKey = ReflectMetadataModule.toKey;
var getOrCreateMetadataMap = ReflectMetadataModule.getMap;
var store = ReflectMetadataModule.store;

// `Reflect.deleteMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  deleteMetadata: function deleteMetadata(metadataKey, target /* , targetKey */) {
    var targetKey = arguments.length < 3 ? undefined : toMetadataKey(arguments[2]);
    var metadataMap = getOrCreateMetadataMap(anObject(target), targetKey, false);
    if (metadataMap === undefined || !metadataMap['delete'](metadataKey)) return false;
    if (metadataMap.size) return true;
    var targetMetadata = store.get(target);
    targetMetadata['delete'](targetKey);
    return !!targetMetadata.size || store['delete'](target);
  }
});


/***/ }),

/***/ 87913:
/*!**************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.get-metadata-keys.js ***!
  \**************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var uncurryThis = __webpack_require__(/*! ../internals/function-uncurry-this */ 42006);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var getPrototypeOf = __webpack_require__(/*! ../internals/object-get-prototype-of */ 45456);
var $arrayUniqueBy = __webpack_require__(/*! ../internals/array-unique-by */ 22117);

var arrayUniqueBy = uncurryThis($arrayUniqueBy);
var concat = uncurryThis([].concat);
var ordinaryOwnMetadataKeys = ReflectMetadataModule.keys;
var toMetadataKey = ReflectMetadataModule.toKey;

var ordinaryMetadataKeys = function (O, P) {
  var oKeys = ordinaryOwnMetadataKeys(O, P);
  var parent = getPrototypeOf(O);
  if (parent === null) return oKeys;
  var pKeys = ordinaryMetadataKeys(parent, P);
  return pKeys.length ? oKeys.length ? arrayUniqueBy(concat(oKeys, pKeys)) : pKeys : oKeys;
};

// `Reflect.getMetadataKeys` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  getMetadataKeys: function getMetadataKeys(target /* , targetKey */) {
    var targetKey = arguments.length < 2 ? undefined : toMetadataKey(arguments[1]);
    return ordinaryMetadataKeys(anObject(target), targetKey);
  }
});


/***/ }),

/***/ 29262:
/*!*********************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.get-metadata.js ***!
  \*********************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var getPrototypeOf = __webpack_require__(/*! ../internals/object-get-prototype-of */ 45456);

var ordinaryHasOwnMetadata = ReflectMetadataModule.has;
var ordinaryGetOwnMetadata = ReflectMetadataModule.get;
var toMetadataKey = ReflectMetadataModule.toKey;

var ordinaryGetMetadata = function (MetadataKey, O, P) {
  var hasOwn = ordinaryHasOwnMetadata(MetadataKey, O, P);
  if (hasOwn) return ordinaryGetOwnMetadata(MetadataKey, O, P);
  var parent = getPrototypeOf(O);
  return parent !== null ? ordinaryGetMetadata(MetadataKey, parent, P) : undefined;
};

// `Reflect.getMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  getMetadata: function getMetadata(metadataKey, target /* , targetKey */) {
    var targetKey = arguments.length < 3 ? undefined : toMetadataKey(arguments[2]);
    return ordinaryGetMetadata(metadataKey, anObject(target), targetKey);
  }
});


/***/ }),

/***/ 16294:
/*!******************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.get-own-metadata-keys.js ***!
  \******************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var ordinaryOwnMetadataKeys = ReflectMetadataModule.keys;
var toMetadataKey = ReflectMetadataModule.toKey;

// `Reflect.getOwnMetadataKeys` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  getOwnMetadataKeys: function getOwnMetadataKeys(target /* , targetKey */) {
    var targetKey = arguments.length < 2 ? undefined : toMetadataKey(arguments[1]);
    return ordinaryOwnMetadataKeys(anObject(target), targetKey);
  }
});


/***/ }),

/***/ 69835:
/*!*************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.get-own-metadata.js ***!
  \*************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var ordinaryGetOwnMetadata = ReflectMetadataModule.get;
var toMetadataKey = ReflectMetadataModule.toKey;

// `Reflect.getOwnMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  getOwnMetadata: function getOwnMetadata(metadataKey, target /* , targetKey */) {
    var targetKey = arguments.length < 3 ? undefined : toMetadataKey(arguments[2]);
    return ordinaryGetOwnMetadata(metadataKey, anObject(target), targetKey);
  }
});


/***/ }),

/***/ 20443:
/*!*********************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.has-metadata.js ***!
  \*********************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);
var getPrototypeOf = __webpack_require__(/*! ../internals/object-get-prototype-of */ 45456);

var ordinaryHasOwnMetadata = ReflectMetadataModule.has;
var toMetadataKey = ReflectMetadataModule.toKey;

var ordinaryHasMetadata = function (MetadataKey, O, P) {
  var hasOwn = ordinaryHasOwnMetadata(MetadataKey, O, P);
  if (hasOwn) return true;
  var parent = getPrototypeOf(O);
  return parent !== null ? ordinaryHasMetadata(MetadataKey, parent, P) : false;
};

// `Reflect.hasMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  hasMetadata: function hasMetadata(metadataKey, target /* , targetKey */) {
    var targetKey = arguments.length < 3 ? undefined : toMetadataKey(arguments[2]);
    return ordinaryHasMetadata(metadataKey, anObject(target), targetKey);
  }
});


/***/ }),

/***/ 8890:
/*!*************************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.has-own-metadata.js ***!
  \*************************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var ordinaryHasOwnMetadata = ReflectMetadataModule.has;
var toMetadataKey = ReflectMetadataModule.toKey;

// `Reflect.hasOwnMetadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  hasOwnMetadata: function hasOwnMetadata(metadataKey, target /* , targetKey */) {
    var targetKey = arguments.length < 3 ? undefined : toMetadataKey(arguments[2]);
    return ordinaryHasOwnMetadata(metadataKey, anObject(target), targetKey);
  }
});


/***/ }),

/***/ 85642:
/*!*****************************************************************!*\
  !*** ./node_modules/core-js/modules/esnext.reflect.metadata.js ***!
  \*****************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

var $ = __webpack_require__(/*! ../internals/export */ 664);
var ReflectMetadataModule = __webpack_require__(/*! ../internals/reflect-metadata */ 88078);
var anObject = __webpack_require__(/*! ../internals/an-object */ 16583);

var toMetadataKey = ReflectMetadataModule.toKey;
var ordinaryDefineOwnMetadata = ReflectMetadataModule.set;

// `Reflect.metadata` method
// https://github.com/rbuckton/reflect-metadata
$({ target: 'Reflect', stat: true }, {
  metadata: function metadata(metadataKey, metadataValue) {
    return function decorator(target, key) {
      ordinaryDefineOwnMetadata(metadataKey, metadataValue, anObject(target), toMetadataKey(key));
    };
  }
});


/***/ }),

/***/ 41197:
/*!************************************************************!*\
  !*** ./node_modules/core-js/proposals/reflect-metadata.js ***!
  \************************************************************/
/***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {

// https://github.com/rbuckton/reflect-metadata
__webpack_require__(/*! ../modules/esnext.reflect.define-metadata */ 95482);
__webpack_require__(/*! ../modules/esnext.reflect.delete-metadata */ 94701);
__webpack_require__(/*! ../modules/esnext.reflect.get-metadata */ 29262);
__webpack_require__(/*! ../modules/esnext.reflect.get-metadata-keys */ 87913);
__webpack_require__(/*! ../modules/esnext.reflect.get-own-metadata */ 69835);
__webpack_require__(/*! ../modules/esnext.reflect.get-own-metadata-keys */ 16294);
__webpack_require__(/*! ../modules/esnext.reflect.has-metadata */ 20443);
__webpack_require__(/*! ../modules/esnext.reflect.has-own-metadata */ 8890);
__webpack_require__(/*! ../modules/esnext.reflect.metadata */ 85642);


/***/ })

},
/******/ __webpack_require__ => { // webpackRuntimeModules
/******/ var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
/******/ var __webpack_exports__ = (__webpack_exec__(7435), __webpack_exec__(41197));
/******/ }
]);
//# sourceMappingURL=polyfills.js.map