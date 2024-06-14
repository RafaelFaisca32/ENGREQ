import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { TranshumanceRequest } from './transhumance-request.model';

@Injectable({ providedIn: 'root' })
export class TranshumanceRequestService {
  private resourceUrl = ApiService.API_URL + '/transhumance-requests';

  constructor(protected http: HttpClient) {}

  create(transhumanceRequest: TranshumanceRequest): Observable<HttpResponse<TranshumanceRequest>> {
    return this.http.post<TranshumanceRequest>(this.resourceUrl, transhumanceRequest, { observe: 'response' });
  }

  update(transhumanceRequest: TranshumanceRequest): Observable<HttpResponse<TranshumanceRequest>> {
    return this.http.put(`${this.resourceUrl}/${transhumanceRequest.id}`, transhumanceRequest, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<TranshumanceRequest>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<TranshumanceRequest[]>> {
    const options = createRequestOption(req);
    return this.http.get<TranshumanceRequest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
