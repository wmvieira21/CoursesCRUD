import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Course } from '../model/course';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.scss'],
})
export class CourseListComponent implements OnInit {
  @Input() courses: Course[] = [];
  @Output('onAddEventEmitter') onAddEventEmitter = new EventEmitter(false);
  @Output('onEditEventEmitter') onEditEventEmitter = new EventEmitter<Course>(false);
  @Output('onDeleteEventEmitter') onDeleteEventEmitter = new EventEmitter<string>(false);

  readonly displayedColumns = ['name', 'category', 'actions'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.onAddEventEmitter.emit(true);
  }

  onEdit(course: Course) {
    this.onEditEventEmitter.emit(course);
  }

  onDelete(id: string) {
    this.onDeleteEventEmitter.emit(id);
  }
}
