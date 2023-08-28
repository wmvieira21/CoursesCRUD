import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from './shared-components/components/error-dialog/error-dialog.component';
import { SharedAPIModule } from './api-module/shared-api.module';
import { CategoryPipe } from './pipes/category.pipe';
import { ConfirmationDialogComponent } from './shared-components/components/confirmation-dialog/confirmation-dialog.component';

@NgModule({
  declarations: [
    ErrorDialogComponent,
    CategoryPipe,
    ConfirmationDialogComponent,
  ],
  imports: [CommonModule, SharedAPIModule],
  exports: [
    ErrorDialogComponent,
    CategoryPipe,
    SharedAPIModule,
    ConfirmationDialogComponent,
  ],
})
export class SharedModule {}
