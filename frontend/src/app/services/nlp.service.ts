import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';

@Injectable({
  providedIn: 'root'
})
export class NlpService {

  constructor(private axiosService: AxiosService) { }

  getComplexity(text: string): Promise<any> {
    return this.axiosService.request("POST", '/complexity', { text: text });
  }
}
