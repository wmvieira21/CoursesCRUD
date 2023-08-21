import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpHeaderResponse } from '@angular/common/http';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar
  ) {
    this.form = formBuilder.group({
      name: [null],
      category: [null],
    });
  }

  ngOnInit(): void {}

  onSave() {
    this.saveCourse(this.form.value);
  }

  saveCourse(record: Course) {
    this.service.postSaveCourse(record).subscribe(
      (result) => {
        console.log(result);
      },
      (error) => this.onError(error)
    );
  }

  private onError(err: HttpHeaderResponse) {
    console.log(err);
    this._snackBar.open('Error as saving: ' + err.statusText, '', {
      duration: 3000,
    });
  }

  onCancel() {}
}
