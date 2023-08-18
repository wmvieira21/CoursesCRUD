import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Course } from './../model/course';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  private readonly URIGetCourses: string = 'api/courses';

  constructor(private client: HttpClient) {}

  getAllCourses(): Observable<Course[]> {
    return this.client.get<Course[]>(this.URIGetCourses);
  }
}
