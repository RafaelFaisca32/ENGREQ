import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Inspection } from './inspection.model';

@Injectable({ providedIn: 'root' })
export class InspectionService {
  private resourceUrl = ApiService.API_URL + '/inspections';

  constructor(protected http: HttpClient) {}

  create(inspection: Inspection): Observable<HttpResponse<Inspection>> {
    return this.http.post<Inspection>(this.resourceUrl, inspection, { observe: 'response' });
  }

  update(inspection: Inspection): Observable<HttpResponse<Inspection>> {
    return this.http.put(`${this.resourceUrl}/${inspection.id}`, inspection, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Inspection>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Inspection[]>> {
    const options = createRequestOption(req);
    return this.http.get<Inspection[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
