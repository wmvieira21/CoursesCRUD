import { Location } from '@angular/common';
import { HttpHeaderResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { ActivatedRoute } from '@angular/router';
import { Lesson } from '../model/lesson';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  form!: FormGroup;

  // form = this.formBuilder.group({
  //   _id: [''],
  //   name: [
  //     '',
  //     [Validators.required, Validators.maxLength(100), Validators.minLength(5)],
  //   ],
  //   category: ['', Validators.required],
  // });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['courseResolv'];

    this.form = this.formBuilder.group({
      _id: [course._id],
      name: [
        course.name,
        [
          Validators.required,
          Validators.maxLength(100),
          Validators.minLength(5),
        ],
      ],
      category: [course.category, Validators.required],
      lessons: this.formBuilder.array(this.retrieveLessons(course))
    });
    console.log(this.form);
  }

  onSave() {
    this.saveCourse(this.form.value);
  }

  saveCourse(record: Partial<Course>) {
    this.service.saveCourse(record).subscribe(
      (result) => this.onSucess(),
      (error) => this.onError(error)
    );
  }

  onCancel() {
    this.location.back();
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

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

  private retrieveLessons(course: Course) {
    const lessons = [];
    if (course?.lessons) {
      course.lessons.forEach((lesson) =>
        lessons.push(this.createLesson(lesson))
      );
    } else {
      lessons.push(this.createLesson());
    }
    return lessons;
  }

  private createLesson(lesson: Lesson = { id: '', name: '', youtubeUrl: '' }) {
    return this.formBuilder.group({
      id: new FormControl([lesson.id]),
      name: new FormControl([lesson.name]),
      youtubeUrl: new FormControl([lesson.youtubeUrl]),
    });
  }
}
