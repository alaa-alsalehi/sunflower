/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.compose.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentManager
import com.google.samples.apps.sunflower.databinding.HomeViewPagerFragmentBinding

@Composable
fun Home(
  supportFragmentManager: FragmentManager,
  onPlantClicked: (String) -> Unit
) {
  val lifecycle = LocalLifecycleOwner.current
  val currentOnPlantClicked by rememberUpdatedState(newValue = onPlantClicked)
  AndroidViewBinding(factory = { inflater, parent, attachToParent ->
    supportFragmentManager.setFragmentResultListener(
      "plantDetailRequestKey",
      lifecycle
    ) { _, bundle ->
      bundle.getString("plantId")?.let {
        currentOnPlantClicked(it)
      }
    }
    HomeViewPagerFragmentBinding.inflate(inflater, parent, attachToParent)
  })
}