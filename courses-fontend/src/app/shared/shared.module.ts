import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from './shared-components/components/error-dialog/error-dialog.component';
import { SharedAPIModule } from './api-module/shared-api.module';
import { CategoryPipe } from './pipes/category.pipe';

@NgModule({
  declarations: [ErrorDialogComponent, CategoryPipe],
  imports: [CommonModule, SharedAPIModule],
  exports: [ErrorDialogComponent, CategoryPipe, SharedAPIModule],
})
export class SharedModule {}
