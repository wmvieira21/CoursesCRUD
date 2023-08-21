import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Course } from './../model/course';
import { Observable, first } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  private readonly URIGetCourses: string = 'api/courses';

  constructor(private client: HttpClient) {}

  getAllCourses(): Observable<Course[]> {
    return this.client.get<Course[]>(this.URIGetCourses);
  }

  postSaveCourse(course: Course) {
    return this.client.post<Course>(this.URIGetCourses, course).pipe(first());
  }
}
