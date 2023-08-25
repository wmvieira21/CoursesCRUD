import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { of } from 'rxjs';
import { CoursesService } from '../services/courses.service';
import { Course } from '../model/course';

export const courseResolver: ResolveFn<Course> = (route, state) => {

  if (route.params['id']) {
    return inject(CoursesService).getCourseByID(route.params['id']);
  }

  return of<Course>({ _id: '', name: '', category: '' });
};
