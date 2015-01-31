/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.vcs.changes.actions.diff;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.diff.chains.DiffRequestPresentableException;
import com.intellij.diff.impl.DiffViewerWrapper;
import com.intellij.openapi.vcs.changes.Change;
import com.intellij.util.ThreeState;
import org.jetbrains.annotations.NotNull;

public interface ChangeDiffViewerWrapperProvider {
  ExtensionPointName<ChangeDiffViewerWrapperProvider> EP_NAME =
    ExtensionPointName.create("com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider");

  @NotNull
  ThreeState isEquals(@NotNull Change change1, @NotNull Change change2);

  boolean canCreate(@NotNull Project project, @NotNull Change change);

  @NotNull
  DiffViewerWrapper process(@NotNull ChangeDiffRequestPresentable presentable,
                            @NotNull UserDataHolder context,
                            @NotNull ProgressIndicator indicator) throws DiffRequestPresentableException, ProcessCanceledException;
}
