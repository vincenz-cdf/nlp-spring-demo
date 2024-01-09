import { Component } from '@angular/core';
import { Subject, debounceTime } from 'rxjs';
import { NlpService } from './services/nlp.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-analyzer';
  text = '';
  wordCount = 0;
  complexityLevel: any;
  sentiment = '';
  loading = false;
  textChange = new Subject<string>(); // Subject to emit text changes

  ngOnInit() {
    this.textChange.pipe(
      debounceTime(1000)
    ).subscribe(value => {
      this.calculateComplexity(value);
      this.loading = false;
    });
  }

  constructor(
    private NlpService: NlpService
  ) {
    // Debounce text input
    this.textChange.pipe(
      debounceTime(1000) // Wait for 1 second of inactivity
    ).subscribe((text: any) => {
      this.calculateComplexity(text);
    });
  }

  onTextChange(text: string) {
    this.loading = true;
    this.wordCount = text.trim().split(/\s+/).filter(word => word.length > 0).length;
    this.textChange.next(text); // Emit the current text
  }

  calculateComplexity(text: string) {
    this.NlpService.getComplexity(text).then((response: any) => {
      this.complexityLevel = response.data.data.complexity;
      this.sentiment = response.data.data.sentiment; 
    }).catch((error: any) => {
      console.log(error);
    } );
  }

  get complexity() {
    if (this.complexityLevel <= 5) {
      return 'Your text is very easy to read and understand by an average 5th-grade student';
    } else if (this.complexityLevel <= 8) {
      return 'Your text suitable for middle school students';
    } else if (this.complexityLevel <= 12) {
      return 'Your text is more appropriate for high school students';
    } else if (this.complexityLevel <= 16) {
      return 'Your text is suitable for college students and graduates';
    } else {
      return 'Your text is likely to be understood by university graduates and post-graduate students';
    }
  }

  get sentimentColor() {
    switch (this.sentiment) {
      case 'Very positive':
        return 'darkgreen';
      case 'Positive':
        return 'green';
      case 'Neutral':
        return 'yellow';
      case 'Negative':
        return 'orange';
      case 'Very negative':
        return 'red';
      default:
        return 'black';
    }
  }
}
