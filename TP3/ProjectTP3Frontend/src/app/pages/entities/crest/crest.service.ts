import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Crest } from './crest.model';

@Injectable({ providedIn: 'root' })
export class CrestService {
  private resourceUrl = ApiService.API_URL + '/crests';

  constructor(protected http: HttpClient) {}

  create(crest: Crest): Observable<HttpResponse<Crest>> {
    return this.http.post<Crest>(this.resourceUrl, crest, { observe: 'response' });
  }

  update(crest: Crest): Observable<HttpResponse<Crest>> {
    return this.http.put(`${this.resourceUrl}/${crest.id}`, crest, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Crest>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryByUser(req?: any): Observable<HttpResponse<Crest[]>> {
    const options = createRequestOption(req);
    return this.http.get<Crest[]>(this.resourceUrl + "/byUser", { params: options, observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Crest[]>> {
    const options = createRequestOption(req);
    return this.http.get<Crest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
