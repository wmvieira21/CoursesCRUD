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

  getCourseByID(id: string): Observable<Course> {
    return this.client.get<Course>(`${this.URIGetCourses}/${id}`);
  }

  saveCourse(course: Partial<Course>) {
    if (course._id) {
      return this.updateCourse(course);
    }
    return this.createCourse(course);
  }

  deleteCourse(id: string) {
    return this.client.delete(`${this.URIGetCourses}/${id}`).pipe(first());
  }

  private createCourse(course: Partial<Course>) {
    return this.client.post<Course>(this.URIGetCourses, course).pipe(first());
  }

  private updateCourse(course: Partial<Course>) {
    return this.client
      .put<Course>(`${this.URIGetCourses}/${course._id}`, course)
      .pipe(first());
  }
}
