/*
 * Author: sveera
 */
import { Component, OnInit } from '@angular/core';
import { Hero } from '../heroes/hero';
import { HeroService } from '../../../services/hero.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

  private hero: Hero;

  constructor(private heroService: HeroService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    const id: number = +this.route.snapshot.paramMap.get('id');
    this.heroService.getHero(id).subscribe(((hero) => { this.hero = hero; }));
  }

  goBack(): void {
    this.location.back();
  }

}
