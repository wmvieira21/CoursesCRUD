import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesComponent } from './courses/courses.component';
import { CourseFormComponent } from './course-form/course-form.component';
import { courseResolver } from './guards/course.resolver';

const routes: Routes = [
  { path: '', component: CoursesComponent },
  {
    path: 'new',
    component: CourseFormComponent,
    resolve: { courseResolv: courseResolver },
  },
  {
    path: 'edit/:id',
    component: CourseFormComponent,
    resolve: { courseResolv: courseResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoursesRoutingModule {}
