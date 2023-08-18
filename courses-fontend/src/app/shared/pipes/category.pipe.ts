import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category',
})
export class CategoryPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case 'frontend':
        return 'code';
      case 'backend':
        return 'computer';
    }
    return 'code';
  }
}
