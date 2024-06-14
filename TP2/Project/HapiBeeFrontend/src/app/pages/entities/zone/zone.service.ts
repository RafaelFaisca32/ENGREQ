import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Zone } from './zone.model';

@Injectable({ providedIn: 'root' })
export class ZoneService {
  private resourceUrl = ApiService.API_URL + '/zones';

  constructor(protected http: HttpClient) {}

  create(zone: Zone): Observable<HttpResponse<Zone>> {
    return this.http.post<Zone>(this.resourceUrl, zone, { observe: 'response' });
  }

  update(zone: Zone): Observable<HttpResponse<Zone>> {
    return this.http.put(`${this.resourceUrl}/${zone.id}`, zone, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Zone>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Zone[]>> {
    const options = createRequestOption(req);
    return this.http.get<Zone[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
