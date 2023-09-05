import { Location } from '@angular/common';
import { HttpHeaderResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  UntypedFormArray,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { ActivatedRoute } from '@angular/router';
import { Lesson } from '../model/lesson';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
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
      lessons: this.formBuilder.array(this.retrieveLessons(course), [
        Validators.required,
      ]),
    });
    console.log(this.form);
  }

  onSave() {
    if (this.form.valid) {
      this.saveCourse(this.form.value);
    } else {
      this.formUtils.validateAllFormControls(this.form);
    }
  }

  saveCourse(record: Partial<Course>) {
    this.service.saveCourse(record).subscribe(
      (next) => this.onSucess(),
      (error) => this.onError(error)
    );
  }

  onCancel() {
    this.location.back();
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
      id: [lesson.id],
      name: [lesson.name, [Validators.required]],
      youtubeUrl: [lesson.youtubeUrl, [Validators.required]],
    });
  }

  getLessonsFormArray() {
    return (<UntypedFormArray>this.form.get('lessons')).controls;
  }

  addNewLesson() {
    const lessons = <UntypedFormArray>this.form.get('lessons');
    lessons.push(this.createLesson());
    lessons.markAsTouched({ onlySelf: true });
  }
  removeLesson(index: number) {
    const lessons = <UntypedFormArray>this.form.get('lessons');
    lessons.removeAt(index);
  }
}
