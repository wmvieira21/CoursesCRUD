import { Injectable } from '@angular/core';
import {
  FormArray,
  FormGroup,
  UntypedFormArray,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class FormUtilsService {
  constructor() {}

  getErrorMessage(form: UntypedFormGroup, fieldName: string) {
    const field = form.get(fieldName) as UntypedFormControl;
    return this.getErrorMessageField(field);
  }

  getErrorMessageField(field: UntypedFormControl) {
    if (field?.hasError('required')) {
      return 'You must enter a value';
    } else if (field?.hasError('maxlength')) {
      const requiredLenght = field.errors
        ? field.errors['maxlength']['requiredLength']
        : 0;
      return `The name must be a maximum of ${requiredLenght} characters`;
    } else if (field?.hasError('minlength')) {
      const requiredLenght = field.errors
        ? field.errors['minlength']['requiredLength']
        : 0;
      return `The name must be a minimum of ${requiredLenght} characters`;
    }
    return '';
  }

  getFormArrayErrors(
    formGroup: UntypedFormGroup,
    fieldArray: string,
    fieldName: string,
    index: number
  ) {
    const formArray = formGroup.get(fieldArray) as UntypedFormArray;
    const field = formArray.controls[index].get(
      fieldName
    ) as UntypedFormControl;
    return this.getErrorMessageField(field);
  }

  isFormArrayInvalid(form: UntypedFormGroup, formArrayName: string) {
    const formArray = <UntypedFormArray>form.get(formArrayName);
    return formArray.invalid && formArray.touched && formArray.length === 0;
  }

  validateAllFormControls(formGroup: UntypedFormGroup | UntypedFormArray) {
    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);

      if (control instanceof UntypedFormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (
        control instanceof UntypedFormGroup ||
        control instanceof UntypedFormArray
      ) {
        control.markAsTouched({ onlySelf: true });
        this.validateAllFormControls(control);
      }
    });
  }
}
