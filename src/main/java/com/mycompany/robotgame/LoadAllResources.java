/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Dendra
 */
public class LoadAllResources {

    static HashMap<String, Image> mapOfAllImages = new HashMap<String, Image>();
    static HashMap<String, AudioClip> mapOfAllSounds = new HashMap<String, AudioClip>();

    public LoadAllResources() {
        Image image = new Image("/EvilDrone/drone_idle1.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle1", image);

        image = new Image("/EvilDrone/drone_idle2.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle2", image);

        image = new Image("/EvilDrone/drone_idle1_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle1Damaged", image);

        image = new Image("/EvilDrone/drone_idle2_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle2Damaged", image);

        image = new Image("/EvilDroneDeath/drone_death1.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death1", image);

        image = new Image("/EvilDroneDeath/drone_death2.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death2", image);

        image = new Image("/EvilDroneDeath/drone_death3.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death3", image);

        image = new Image("/EvilDroneDeath/drone_death4.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death4", image);

        image = new Image("/EvilDroneDeath/drone_death5.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death5", image);

        image = new Image("/MinigunsHits/drone_hit1.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit1", image);

        image = new Image("/MinigunsHits/drone_hit2.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit2", image);

        image = new Image("/MinigunsHits/drone_hit3.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit3", image);

        image = new Image("/Cursor/cursor_target.png", 32, 32, false, false);
        mapOfAllImages.put("cursorTarget", image);

        image = new Image("/wreckage/wreckage.png", 512, 512, false, false);
        mapOfAllImages.put("spaceShipWreckage", image);

        loadShieldImages();
        loadTerrainImages();
        loadStaticTurretPictures();
        loadRocketImage();
        loadMinigunMissImages();
        loadPlayerRobotImages();
        loadCliffs();
        loadMinigunHits();
        loadFortressWalls();
        loadSatelite();
        loadSpiderRobot();
        loadSpiderEnergyShock();
        loadSpiderLaser();
        loadCraters();
        loadEvilDroneMarkTwo();
        loadLaserBurnedGround();
        loadInterfaceBar();
        loadDroneBarracks();
        loadAllContainers();
        loadAllRocks();
        loadSkeletons();
        loadBomberAirplane();
        loadRoads();
        loadDryLand();
        loadAncientRemains();

        loadAllSounds();
    }
    
    private void loadAncientRemains(){
        Image image = new Image("/TitanRemains/ancient_face.png", 512, 512, false, false);
        mapOfAllImages.put("TitanFace", image);
    }
    
    private void loadDryLand() {
        Image image = new Image("/DryLand/Dry_land_horizontal.png", 512, 256, false, false);
        mapOfAllImages.put("Dry_land_horizontal", image);

        image = new Image("/DryLand/Dry_land_large_one.png", 512, 512, false, false);
        mapOfAllImages.put("Dry_land_large_one", image);
        
        image = new Image("/DryLand/Dry_land_large_two.png", 512, 512, false, false);
        mapOfAllImages.put("Dry_land_large_two", image);
        
        image = new Image("/DryLand/Dry_land_small_one.png", 256, 256, false, false);
        mapOfAllImages.put("Dry_land_small_one", image);
        
        image = new Image("/DryLand/Dry_land_small_two.png", 256, 256, false, false);
        mapOfAllImages.put("Dry_land_small_two", image);
        
        image = new Image("/DryLand/Dry_land_small_three.png", 256, 256, false, false);
        mapOfAllImages.put("Dry_land_small_three", image);
        
        image = new Image("/DryLand/Dry_land_vertical.png", 256, 512, false, false);
        mapOfAllImages.put("Dry_land_vertical", image);
    }

    private void loadRoads() {
        Image image = new Image("/Road/Road_end_north_smooth.png", 424, 424, false, false);
        mapOfAllImages.put("Road_end_north_smooth", image);

        image = new Image("/Road/Road_end_south_bridge.png", 424, 424, false, false);
        mapOfAllImages.put("Road_end_south_bridge", image);

        image = new Image("/Road/Road_straight_north_south.png", 424, 424, false, false);
        mapOfAllImages.put("Road_straight_north_south", image);

        image = new Image("/Road/Road_straight_north_south_breaks.png", 424, 424, false, false);
        mapOfAllImages.put("Road_straight_north_south_breaks", image);
        
        image = new Image("/Road/Road_straight_north_south_burns.png", 424, 424, false, false);
        mapOfAllImages.put("Road_straight_north_south_burns", image);

        image = new Image("/Road/Road_straight_north_south_cracks.png", 424, 424, false, false);
        mapOfAllImages.put("Road_straight_north_south_cracks", image);

        image = new Image("/Road/Road_straight_north_south_crater.png", 424, 424, false, false);
        mapOfAllImages.put("Road_straight_north_south_crater", image);
    }

    private void loadBomberAirplane() {
        Image image = new Image("/FlyingBomber/bomber_idle.png", 64, 64, false, false);
        mapOfAllImages.put("bomberIdle", image);

        image = new Image("/FlyingBomber/bomber_moving_1.png", 64, 64, false, false);
        mapOfAllImages.put("bomberMovingA", image);

        image = new Image("/FlyingBomber/bomber_moving_2.png", 64, 64, false, false);
        mapOfAllImages.put("bomberMovingB", image);

        image = new Image("/FlyingBomber/bomber_death_1.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath1", image);

        image = new Image("/FlyingBomber/bomber_death_2.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath2", image);

        image = new Image("/FlyingBomber/bomber_death_3.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath3", image);

        image = new Image("/FlyingBomber/bomber_death_4.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath4", image);

        image = new Image("/FlyingBomber/bomber_death_5.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath5", image);

        image = new Image("/FlyingBomber/bomber_death_6.png", 192, 192, false, false);
        mapOfAllImages.put("bomberDeath6", image);
    }

    private void loadSkeletons() {
        Image image = new Image("/Skeletons/skeletonDinosaurus.png", 512, 512, false, false);
        mapOfAllImages.put("skeletonDinosaurus", image);
    }

    private void loadAllRocks() {
        Image image = new Image("/Rocks/rock_128_1.png", 128, 128, false, false);
        mapOfAllImages.put("rockMediumOne", image);

        image = new Image("/Rocks/rock_128_2.png", 128, 128, false, false);
        mapOfAllImages.put("rockMediumTwo", image);

        image = new Image("/Rocks/rock_128_3.png", 128, 128, false, false);
        mapOfAllImages.put("rockMediumThree", image);

        image = new Image("/Rocks/rock_256_1.png", 256, 256, false, false);
        mapOfAllImages.put("rockLargeOne", image);

        image = new Image("/Rocks/rock_256_2.png", 256, 256, false, false);
        mapOfAllImages.put("rockLargeTwo", image);
    }

    private void loadAllContainers() {
        Image image = new Image("/Containers/box.png", 128, 128, false, false);
        mapOfAllImages.put("box1", image);

        image = new Image("/Containers/container1.png", 128, 256, false, false);
        mapOfAllImages.put("container1", image);

        image = new Image("/Containers/container2.png", 256, 128, false, false);
        mapOfAllImages.put("container2", image);

        image = new Image("/Containers/box2.png", 258, 258, false, false);
        mapOfAllImages.put("box2", image);
    }

    private void loadDroneBarracks() {
        Image image = new Image("/DroneBarracks/drone_barracks_1.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks1", image);

        image = new Image("/DroneBarracks/drone_barracks_2.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks2", image);

        image = new Image("/DroneBarracks/drone_barracks_3.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks3", image);

        image = new Image("/DroneBarracks/drone_barracks_4.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks4", image);

        image = new Image("/DroneBarracks/drone_barracks_5.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks5", image);

        image = new Image("/DroneBarracks/drone_barracks_6.png", 256, 384, false, false);
        mapOfAllImages.put("droneBarracks6", image);
    }

    private void loadInterfaceBar() {
        Image image = new Image("/InterfaceBar/bar_intro_1.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro1", image);

        image = new Image("/InterfaceBar/bar_intro_2.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro2", image);

        image = new Image("/InterfaceBar/bar_intro_3.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro3", image);

        image = new Image("/InterfaceBar/bar_intro_4.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro4", image);

        image = new Image("/InterfaceBar/bar_intro_5.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro5", image);

        image = new Image("/InterfaceBar/bar_intro_6.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro6", image);

        image = new Image("/InterfaceBar/bar_intro_7.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro7", image);

        image = new Image("/InterfaceBar/bar_intro_8.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro8", image);

        image = new Image("/InterfaceBar/bar_intro_9.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro9", image);

        image = new Image("/InterfaceBar/bar_intro_10.png", 308, 41, false, false);
        mapOfAllImages.put("barIntro10", image);

        image = new Image("/InterfaceBar/bar_bottom.png", 308, 36, false, false);
        mapOfAllImages.put("barWrapperBottom", image);

        image = new Image("/InterfaceBar/bar_top.png", 308, 37, false, false);
        mapOfAllImages.put("barWrapperTop", image);

        image = new Image("/InterfaceBar/bar_hull_integrity.png", 308, 41, false, false);
        mapOfAllImages.put("TextHullIntergrity", image);

        image = new Image("/InterfaceBar/bar_weapon_heat.png", 308, 41, false, false);
        mapOfAllImages.put("TextWeaponHeat", image);

        image = new Image("/InterfaceBar/bar_shield_energy.png", 308, 41, false, false);
        mapOfAllImages.put("TextShieldEnergy", image);

        image = new Image("/InterfaceBar/bar_0.png", 308, 41, false, false);
        mapOfAllImages.put("displayZero", image);

        image = new Image("/InterfaceBar/bar_1.png", 308, 41, false, false);
        mapOfAllImages.put("displayOne", image);

        image = new Image("/InterfaceBar/bar_1-2.png", 308, 41, false, false);
        mapOfAllImages.put("displayTwo", image);

        image = new Image("/InterfaceBar/bar_1-3.png", 308, 41, false, false);
        mapOfAllImages.put("displayThree", image);

        image = new Image("/InterfaceBar/bar_1-4.png", 308, 41, false, false);
        mapOfAllImages.put("displayFour", image);

        image = new Image("/InterfaceBar/bar_1-5.png", 308, 41, false, false);
        mapOfAllImages.put("displayFive", image);

        image = new Image("/InterfaceBar/bar_1-6.png", 308, 41, false, false);
        mapOfAllImages.put("displaySix", image);

        image = new Image("/InterfaceBar/bar_1-7.png", 308, 41, false, false);
        mapOfAllImages.put("displaySeven", image);

        image = new Image("/InterfaceBar/bar_1-8.png", 308, 41, false, false);
        mapOfAllImages.put("displayEight", image);

        image = new Image("/InterfaceBar/bar_1-9.png", 308, 41, false, false);
        mapOfAllImages.put("displayNine", image);

        image = new Image("/InterfaceBar/bar_1-10.png", 308, 41, false, false);
        mapOfAllImages.put("displayTen", image);
    }

    private void loadLaserBurnedGround() {
        Image image = new Image("/LaserBurnedGround/laser_burns_1.png", 32, 32, false, false);
        mapOfAllImages.put("laserBurnedGround1", image);

        image = new Image("/LaserBurnedGround/laser_burns_2.png", 32, 32, false, false);
        mapOfAllImages.put("laserBurnedGround2", image);

        image = new Image("/LaserBurnedGround/laser_burns_3.png", 32, 32, false, false);
        mapOfAllImages.put("laserBurnedGround3", image);

        image = new Image("/LaserBurnedGround/laser_burns_4.png", 32, 32, false, false);
        mapOfAllImages.put("laserBurnedGround4", image);
    }

    private void loadSpiderLaser() {
        Image image = new Image("/SpiderRobot/Laser/walker_tower_laser_1.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser1", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_2.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser2", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_11.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser11", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_12.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser12", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_21.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser21", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_22.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser22", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_31.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser31", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_32.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser32", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_41.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser41", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_42.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser42", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_51.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser51", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_52.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser52", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_61.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser61", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_62.png", 512, 512, false, false);
        mapOfAllImages.put("spiderLaser62", image);
        
        image = new Image("/SpiderRobot/Laser/walker_tower_laser_71.png", 576, 576, false, false);
        mapOfAllImages.put("spiderLaser71", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_72.png", 576, 576, false, false);
        mapOfAllImages.put("spiderLaser72", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_81.png", 640, 640, false, false);
        mapOfAllImages.put("spiderLaser81", image);

        image = new Image("/SpiderRobot/Laser/walker_tower_laser_82.png", 640, 640, false, false);
        mapOfAllImages.put("spiderLaser82", image);
    }

    private void loadSpiderEnergyShock() {
        Image image = new Image("/SpiderRobot/EnergyShock/spider_lightning_1b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning1B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_2b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning2B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_3b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning3B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_4b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning4B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_5b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning5B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_6b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning6B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_7b.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning7B", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_1a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning1A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_2a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning2A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_3a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning3A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_4a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning4A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_5a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning5A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_6a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning6A", image);

        image = new Image("/SpiderRobot/EnergyShock/spider_lightning_7a.png", 256, 256, false, false);
        mapOfAllImages.put("spiderLightning7A", image);
    }

    private void loadEvilDroneMarkTwo() {
        Image image = new Image("/EvilDroneMarkTwo/drone_1.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwo1", image);

        image = new Image("/EvilDroneMarkTwo/drone_2.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwo2", image);

        image = new Image("/EvilDroneMarkTwo/drone_3.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwo3", image);

        image = new Image("/EvilDroneMarkTwo/drone_4.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwo4", image);

        image = new Image("/EvilDroneMarkTwo/drone_damaged_1.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwoDamaged1", image);

        image = new Image("/EvilDroneMarkTwo/drone_damaged_2.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneMarkTwoDamaged2", image);
    }

    private void loadCraters() {
        Image image = new Image("/Craters/krater_128_2.png", 128, 128, false, false);
        mapOfAllImages.put("crater128", image);
        
        image = new Image("/Craters/krater_128_1.png", 128, 128, false, false);
        mapOfAllImages.put("crater128_2", image);

        image = new Image("/Craters/krater_256.png", 256, 256, false, false);
        mapOfAllImages.put("crater256", image);

        image = new Image("/Craters/krater_64_1.png", 64, 64, false, false);
        mapOfAllImages.put("crater64_1", image);

        image = new Image("/Craters/krater_64_2.png", 64, 64, false, false);
        mapOfAllImages.put("crater64_2", image);

        image = new Image("/Craters/krater_64_3.png", 64, 64, false, false);
        mapOfAllImages.put("crater64_3", image);
    }

    private void loadSpiderRobot() {
        Image image = new Image("/SpiderRobot/walker_idle.png", 256, 256, false, false);
        mapOfAllImages.put("walker_idle", image);

        image = new Image("/SpiderRobot/walker_moving_1.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_1", image);

        image = new Image("/SpiderRobot/walker_moving_1,5.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_2", image);

        image = new Image("/SpiderRobot/walker_moving_2.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_3", image);

        image = new Image("/SpiderRobot/walker_moving_2,5.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_4", image);

        image = new Image("/SpiderRobot/walker_moving_3.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_5", image);

        image = new Image("/SpiderRobot/walker_moving_3,5.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_6", image);

        image = new Image("/SpiderRobot/walker_moving_4.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_7", image);

        image = new Image("/SpiderRobot/walker_moving_4,5.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_8", image);

        image = new Image("/SpiderRobot/walker_moving_5.png", 256, 256, false, false);
        mapOfAllImages.put("walker_moving_10", image);

        image = new Image("/SpiderRobot/spider_tower_1.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTower1", image);

        image = new Image("/SpiderRobot/spider_tower_2.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTower2", image);

        image = new Image("/SpiderRobot/spider_tower_3.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTower3", image);

        image = new Image("/SpiderRobot/spider_tower_4.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTower4", image);

        image = new Image("/SpiderRobot/spider_tower_damaged_1.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTowerDamaged1", image);

        image = new Image("/SpiderRobot/spider_tower_damaged_2.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTowerDamaged2", image);

        image = new Image("/SpiderRobot/spider_tower_damaged_3.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTowerDamaged3", image);

        image = new Image("/SpiderRobot/spider_tower_damaged_4.png", 128, 128, false, false);
        mapOfAllImages.put("spiderTowerDamaged4", image);

        image = new Image("/SpiderRobot/Explosion/walker_death_1.png", 128, 128, false, false);
        mapOfAllImages.put("spiderExplosion1", image);

        image = new Image("/SpiderRobot/Explosion/walker_death_2.png", 128, 128, false, false);
        mapOfAllImages.put("spiderExplosion2", image);

        image = new Image("/SpiderRobot/Explosion/walker_death_3.png", 128, 128, false, false);
        mapOfAllImages.put("spiderExplosion3", image);

        image = new Image("/SpiderRobot/Explosion/walker_death_4.png", 128, 128, false, false);
        mapOfAllImages.put("spiderExplosion4", image);

        image = new Image("/SpiderRobot/Explosion/walker_death_5.png", 128, 128, false, false);
        mapOfAllImages.put("spiderExplosion5", image);

        image = new Image("/SpiderRobot/walker_corpse.png", 128, 128, false, false);
        mapOfAllImages.put("spiderCorpse", image);
    }

    private void loadSatelite() {
        Image image = new Image("/Satelit/satelite_base.png", 256, 256, false, false);
        mapOfAllImages.put("sateliteBase", image);

        image = new Image("/Satelit/satelite_tower.png", 384, 384, false, false);
        mapOfAllImages.put("sateliteTower", image);
    }

    private void loadFortressWalls() {
        Image image = new Image("/walls/outerWallEast.png", 640, 1530, false, false);
        mapOfAllImages.put("outerWallEast", image);

        image = new Image("/walls/outerWallWest.png", 642, 1532, false, false);
        mapOfAllImages.put("outerWallWest", image);

        image = new Image("/walls/southWall.png", 489, 108, false, false);
        mapOfAllImages.put("southWall", image);

        image = new Image("/walls/NortWall.png", 493, 111, false, false);
        mapOfAllImages.put("northWall", image);
    }

    private void loadMinigunHits() {
        Image image = new Image("/MinigunsHits/blocked_hits_1.png", 32, 32, false, false);
        mapOfAllImages.put("blockedMinigunShot1", image);

        image = new Image("/MinigunsHits/blocked_hits_2.png", 32, 32, false, false);
        mapOfAllImages.put("blockedMinigunShot2", image);

        image = new Image("/MinigunsHits/blocked_hits_3.png", 32, 32, false, false);
        mapOfAllImages.put("blockedMinigunShot3", image);

        image = new Image("/MinigunsHits/blocked_hits_4.png", 32, 32, false, false);
        mapOfAllImages.put("blockedMinigunShot4", image);

        image = new Image("/MinigunsHits/blocked_hits_5.png", 32, 32, false, false);
        mapOfAllImages.put("blockedMinigunShot5", image);
    }

    private void loadCliffs() {
        Image image = new Image("/cliffs/cliffHorizontal.png", 1024, 256, false, false);
        mapOfAllImages.put("cliffHorizontal", image);

        image = new Image("/cliffs/cliffHorizontalReversed.png", 1024, 256, false, false);
        mapOfAllImages.put("cliffHorizontalReversed", image);

        image = new Image("/cliffs/cliffVertical.png", 256, 1024, false, false);
        mapOfAllImages.put("cliffVertical", image);

        image = new Image("/cliffs/cliffVerticalReversed.png", 256, 1024, false, false);
        mapOfAllImages.put("cliffVerticalReversed", image);

        image = new Image("/cliffs/cliff_corner_BottomLeft.png", 256, 256, false, false);
        mapOfAllImages.put("cliffBottomLeftCorner", image);

        image = new Image("/cliffs/cliff_corner_BottomRight.png", 256, 256, false, false);
        mapOfAllImages.put("cliffBottomRightCorner", image);

        image = new Image("/cliffs/cliff_corner_TopLeft.png", 256, 256, false, false);
        mapOfAllImages.put("cliffTopLeftCorner", image);

        image = new Image("/cliffs/cliff_corner_TopRight.png", 256, 256, false, false);
        mapOfAllImages.put("cliffTopRightCorner", image);
    }

    private void loadMinigunMissImages() {
        Image image = new Image("/MinigunsHits/miss_1.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss1", image);

        image = new Image("/MinigunsHits/miss_2.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss2", image);

        image = new Image("/MinigunsHits/miss_3.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss3", image);

        image = new Image("/MinigunsHits/miss_4.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss4", image);

        image = new Image("/MinigunsHits/miss_5.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss5", image);

        image = new Image("/MinigunsHits/miss_6.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss6", image);

        image = new Image("/MinigunsHits/miss_7.png", 64, 64, false, false);
        mapOfAllImages.put("minigunMiss7", image);
    }

    private void loadPlayerRobotImages() {
        Image image = new Image("/playerRobot/shots.png", 256, 256, false, false);
        mapOfAllImages.put("shotsMinigunShorter", image);

        image = new Image("/playerRobot/shots_delsi.png", 256, 256, false, false);
        mapOfAllImages.put("shotsMinigunLonger", image);

        image = new Image("/playerRobot/minigun_fire1.png", 256, 256, false, false);
        mapOfAllImages.put("minigunFire1", image);

        image = new Image("/playerRobot/minigun_fire2.png", 256, 256, false, false);
        mapOfAllImages.put("minigunFire2", image);

        image = new Image("/playerRobot/minigun_fire3.png", 256, 256, false, false);
        mapOfAllImages.put("minigunFire3", image);

        image = new Image("/playerRobot/minigun_fire4.png", 256, 256, false, false);
        mapOfAllImages.put("minigunFire4", image);

        image = new Image("/base_passive.png", 64, 64, false, false);
        mapOfAllImages.put("basePassive", image);

        image = new Image("/base_moving.png", 64, 64, false, false);
        mapOfAllImages.put("baseMoving", image);

        image = new Image("/tower_passive.png", 64, 64, false, false);
        mapOfAllImages.put("towerPassive", image);

        image = new Image("/tower_shooting.png", 64, 64, false, false);
        mapOfAllImages.put("towerShooting", image);
    }

    private void loadShieldImages() {
        Image image = new Image("/Shield/robot_energy_shield_1.png", 128, 128, false, false);
        mapOfAllImages.put("energyShield1", image);

        image = new Image("/Shield/robot_energy_shield_2.png", 128, 128, false, false);
        mapOfAllImages.put("energyShield2", image);

        image = new Image("/Shield/robot_energy_shield_3.png", 128, 128, false, false);
        mapOfAllImages.put("energyShield3", image);
    }

    private void loadTerrainImages() {
        Image image = new Image("/terrain/pisek_1.png", 256, 256, false, false);
        mapOfAllImages.put("terrain1", image);

        image = new Image("/terrain/pisek_1m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain2", image);

        image = new Image("/terrain/pisek_2.png", 256, 256, false, false);
        mapOfAllImages.put("terrain3", image);

        image = new Image("/terrain/pisek_2m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain4", image);

        image = new Image("/terrain/pisek_3.png", 256, 256, false, false);
        mapOfAllImages.put("terrain5", image);

        image = new Image("/terrain/pisek_3m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain6", image);

        image = new Image("/terrain/pisek_4.png", 256, 256, false, false);
        mapOfAllImages.put("terrain7", image);

        image = new Image("/terrain/pisek_4m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain8", image);

        image = new Image("/terrain/pisek_5.png", 256, 256, false, false);
        mapOfAllImages.put("terrain9", image);

        image = new Image("/terrain/pisek_5m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain10", image);

        image = new Image("/terrain/pisek_6.png", 256, 256, false, false);
        mapOfAllImages.put("terrain11", image);

        image = new Image("/terrain/pisek_6m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain12", image);

        image = new Image("/terrain/pisek_7.png", 256, 256, false, false);
        mapOfAllImages.put("terrain13", image);

        image = new Image("/terrain/pisek_7m.png", 256, 256, false, false);
        mapOfAllImages.put("terrain14", image);
    }

    private void loadRocketImage() {
        Image image = new Image("/Rocket/turret_rocket_1.png", 64, 64, false, false);
        mapOfAllImages.put("rocket1", image);

        image = new Image("/Rocket/turret_rocket_2.png", 64, 64, false, false);
        mapOfAllImages.put("rocket2", image);

        image = new Image("/Rocket/rocket_explosion1.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion1", image);

        image = new Image("/Rocket/rocket_explosion2.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion2", image);

        image = new Image("/Rocket/rocket_explosion3.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion3", image);

        image = new Image("/Rocket/rocket_explosion4.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion4", image);

        image = new Image("/Rocket/rocket_explosion5.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion5", image);
    }

    private void loadStaticTurretPictures() {
        Image image = new Image("/StaticTurret/turret_base.png", 64, 64, false, false);
        mapOfAllImages.put("turretBase", image);

        image = new Image("/StaticTurret/turret_idle.png", 64, 64, false, false);
        mapOfAllImages.put("idleTurret", image);

        image = new Image("/StaticTurret/turret_tower.png", 64, 64, false, false);
        mapOfAllImages.put("turretTower", image);

        image = new Image("/StaticTurret/turret_intro_1.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro1", image);

        image = new Image("/StaticTurret/turret_intro_2.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro2", image);

        image = new Image("/StaticTurret/turret_intro_3.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro3", image);

        image = new Image("/StaticTurret/turret_intro_3.5.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro4", image);

        image = new Image("/StaticTurret/turret_intro_4.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro5", image);

        image = new Image("/StaticTurret/turret_tower_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("turretDamaged", image);

        image = new Image("/StaticTurret/turret_death1.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath1", image);

        image = new Image("/StaticTurret/turret_death2.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath2", image);

        image = new Image("/StaticTurret/turret_death3.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath3", image);

        image = new Image("/StaticTurret/turret_death4.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath4", image);

        image = new Image("/StaticTurret/turret_death5.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath5", image);

        image = new Image("/StaticTurret/turret_death6.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath6", image);

        image = new Image("/StaticTurret/turret_base_dead.png", 64, 64, false, false);
        mapOfAllImages.put("turretBaseDead", image);

    }

    private void loadAllSounds() {
   /*     AudioClip audioClip = new AudioClip(this.getClass().getResource("/Sounds/idleRobot.mp3").toExternalForm());
        mapOfAllSounds.put("idleRobotSound", audioClip);

        audioClip = new AudioClip(this.getClass().getResource("/Sounds/movingRobot.mp3").toExternalForm());
        mapOfAllSounds.put("movingRobotSound", audioClip);

        audioClip = new AudioClip(this.getClass().getResource("/Sounds/GaOv.mp3").toExternalForm());
        mapOfAllSounds.put("gameOverSound", audioClip);

        audioClip = new AudioClip(this.getClass().getResource("/Sounds/kulas.mp3").toExternalForm());
        mapOfAllSounds.put("minigunSound", audioClip);

        audioClip = new AudioClip(this.getClass().getResource("/Sounds/dispatch.mp3").toExternalForm());
        mapOfAllSounds.put("dispatchSound", audioClip);*/
    }

    public static HashMap<String, Image> getMapOfAllImages() {
        return mapOfAllImages;
    }

    public static HashMap<String, AudioClip> getMapOfAllSounds() {
        return mapOfAllSounds;
    }

}
