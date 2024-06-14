import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Hive } from './hive.model';

@Injectable({ providedIn: 'root' })
export class HiveService {
  private resourceUrl = ApiService.API_URL + '/hives';

  constructor(protected http: HttpClient) {}

  create(hive: Hive): Observable<HttpResponse<Hive>> {
    return this.http.post<Hive>(this.resourceUrl, hive, { observe: 'response' });
  }

  update(hive: Hive): Observable<HttpResponse<Hive>> {
    return this.http.put(`${this.resourceUrl}/${hive.id}`, hive, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Hive>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Hive[]>> {
    const options = createRequestOption(req);
    return this.http.get<Hive[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
