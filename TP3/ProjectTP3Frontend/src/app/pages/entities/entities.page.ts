import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { NavigationExtras } from '@angular/router';
import { NetworkCheckerService } from '../../services/network/network-checker.service';

@Component({
  selector: 'app-entities',
  templateUrl: 'entities.page.html',
  styleUrls: ['entities.page.scss'],
})
export class EntitiesPage implements OnInit {
  entities: Array<any> = [
    { name: 'Adicionar uma Zona de Apiário', component: 'ApiaryZonePage', route: 'apiary-zone/new', subroute: 'entities' },
    { name: 'Lista de Zonas de Apiário', component: 'ApiaryZonePage', route: 'apiary-zone', subroute: 'entities' },
    { name: 'Adicionar nova doença', component: 'DiseasePage', route: 'disease/new', subroute: 'entities' },
    { name: 'Lista de Doenças', component: 'DiseasePage', route: 'disease', subroute: 'entities' },
    { name: 'Criar um Quadro', component: 'FramePage', route: 'frame/new', subroute: 'entities' },
    { name: 'Lista de Quadros', component: 'FramePage', route: 'frame', subroute: 'entities' },
    { name: 'Criar um Apiário', component: 'ApiaryPage', route: 'apiary/new', subroute:'entities' },
    { name: 'Lista de Apiários', component: 'ApiaryPage', route:'apiary', subroute: 'entities' },
    { name: 'Lista de Colmeias', component: 'HivePage', route:'hive', subroute: 'entities' },
    { name: 'Efetuar Inspeção', component: 'InspectionPage', route: 'apiary' , subroute:'entities', from: 'createInspection' },
    { name: 'Lista de Inspeções', component: 'InspectionPage', route: 'inspection' , subroute:'entities', from: 'listInspection' },
    { name: 'Realizar a Cresta', component: 'CrestPage', route: 'apiary', subroute:'entities', from: 'createCrest' },
    { name: 'Lista de Crestas', component: 'CrestPage', route: 'crest', subroute:'entities', from: 'listCrest' },
    { name: 'Realizar um Desdobramento', component: 'UnfoldingPage', route: 'apiary',  subroute:'entities', from: 'createUnfolding'},
    { name: 'Lista de Desdobramentos', component: 'UnfoldingPage', route: 'unfolding',  subroute:'entities'},
    { name: 'Pedido de Transumância', component: 'TranshumanceRequestPage', route: 'transhumance-request/new', subroute: 'entities' },
    { name: 'Lista de Pedidos de Transumância', component: 'TranshumanceRequestPage', route: 'transhumance-request', subroute: 'entities' },
    { name: 'Submissão da declaração Anual de Existências', component: 'AnnualInventoryDeclarationPage', route: 'annual-inventory-declaration/new', subroute:'entities' },
    { name: 'Lista de declarações Anuais de Existências', component: 'AnnualInventoryDeclarationPage', route: 'annual-inventory-declaration', subroute:'entities' },

    //{ name: 'Beekeeper', component: 'BeekeeperPage', route: 'beekeeper' },
    //{ name: 'Hive', component: 'HivePage', route: 'hive' },
    //{ name: 'Apiary Information', component: 'ApiaryInformationPage', route: 'apiary-information' },
    /* jhipster-needle-add-entity-page - JHipster will add entity pages here */
  ];

  constructor(public navController: NavController,
    private network: NetworkCheckerService) {}

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  openPage(page) {
    let navigationExtras : NavigationExtras;
    if(page.from){
      navigationExtras = { state : { from: page.from }, queryParams : { from: page.from } }
    }
    this.navController.navigateForward('/tabs/'+page.subroute+'/' + page.route, navigationExtras);

  }
}
