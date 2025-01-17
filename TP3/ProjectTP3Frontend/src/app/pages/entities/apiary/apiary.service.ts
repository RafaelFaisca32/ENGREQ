import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Apiary } from './apiary.model';

@Injectable({ providedIn: 'root' })
export class ApiaryService {
  private resourceUrl = ApiService.API_URL + '/apiaries';

  constructor(protected http: HttpClient) {}

  create(apiary: Apiary): Observable<HttpResponse<Apiary>> {
    return this.http.post<Apiary>(this.resourceUrl, apiary, { observe: 'response' });
  }

  update(apiary: Apiary): Observable<HttpResponse<Apiary>> {
    return this.http.put(`${this.resourceUrl}/${apiary.id}`, apiary, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Apiary>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Apiary[]>> {
    const options = createRequestOption(req);
    return this.http.get<Apiary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
