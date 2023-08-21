import { Location } from '@angular/common';
import { HttpHeaderResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  form = this.formBuilder.group({
    name: [''],
    category: ['']
  });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location
  ) {}

  ngOnInit(): void {}

  onSave() {
    this.saveCourse(this.form.value);
  }

  saveCourse(record: Partial<Course>) {
    this.service.postSaveCourse(record).subscribe(
      (result) => this.onSucess(),
      (error) => this.onError(error)
    );
  }

  private onSucess() {
    this._snackBar.open('Course was saved!', '', {
      duration: 3000,
    });
    this.onCancel();
  }
  private onError(err: HttpHeaderResponse) {
    this._snackBar.open('Error as saving: ' + err.statusText, '', {
      duration: 3000,
    });
  }

  onCancel() {
    this.location.back();
  }
}
