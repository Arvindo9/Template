/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.aiprog.template.utils.rx;

import io.reactivex.Scheduler;


/**
 * Author : Arvindo Mondal
 * Email : arvindomondal@gmail.com
 * Created on : 31-Oct-18
 */
public interface SchedulerProvider {

    Scheduler computation();

    /**
     *
     * @return This can be used for asynchronously performing blocking IO.
     */
    Scheduler io();

    Scheduler ui();

    Scheduler single();

    Scheduler trampoline();

    Scheduler newThread();
}
