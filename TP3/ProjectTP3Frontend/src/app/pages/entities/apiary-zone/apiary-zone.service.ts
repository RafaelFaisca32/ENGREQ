import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { ApiaryZone } from './apiary-zone.model';

@Injectable({ providedIn: 'root' })
export class ApiaryZoneService {
  private resourceUrl = ApiService.API_URL + '/apiary-zones';

  constructor(protected http: HttpClient) {}

  create(apiaryZone: ApiaryZone): Observable<HttpResponse<ApiaryZone>> {
    return this.http.post<ApiaryZone>(this.resourceUrl, apiaryZone, { observe: 'response' });
  }

  update(apiaryZone: ApiaryZone): Observable<HttpResponse<ApiaryZone>> {
    return this.http.put(`${this.resourceUrl}/${apiaryZone.id}`, apiaryZone, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<ApiaryZone>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<ApiaryZone[]>> {
    const options = createRequestOption(req);
    return this.http.get<ApiaryZone[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
