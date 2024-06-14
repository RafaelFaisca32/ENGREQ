import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Unfolding } from './unfolding.model';

@Injectable({ providedIn: 'root' })
export class UnfoldingService {
  private resourceUrl = ApiService.API_URL + '/unfoldings';

  constructor(protected http: HttpClient) {}

  create(unfolding: Unfolding): Observable<HttpResponse<Unfolding>> {
    return this.http.post<Unfolding>(this.resourceUrl, unfolding, { observe: 'response' });
  }

  update(unfolding: Unfolding): Observable<HttpResponse<Unfolding>> {
    return this.http.put(`${this.resourceUrl}/${unfolding.id}`, unfolding, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Unfolding>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Unfolding[]>> {
    const options = createRequestOption(req);
    return this.http.get<Unfolding[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
