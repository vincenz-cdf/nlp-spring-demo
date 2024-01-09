import { Injectable } from '@angular/core';
import axios from 'axios';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = environment.baseUrl;
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  request(method: string, url: string, data: any): Promise<any> {
      let headers: any = {};

      return axios({
          method: method,
          url: url,
          data: data,
          headers: headers
      });
  }
}
