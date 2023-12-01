import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProxyService {

  constructor(private http: HttpClient) {}

  proxyRequest(url: string) {
    return this.http.get(`/proxy?url=${url}`);
  }
}
