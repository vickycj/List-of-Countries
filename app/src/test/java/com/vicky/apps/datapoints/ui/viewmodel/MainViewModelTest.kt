package com.vicky.apps.datapoints.ui.viewmodel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.CompanyDetails
import com.vicky.apps.datapoints.ui.model.ResponseData

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {


    @Mock
    lateinit var repository: Repository

    private val schedulerProvider = SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline())

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository, schedulerProvider)
    }

    @After
    fun tearDown() {
    }


    fun getObject(): List<ResponseData>{
        val token = object : TypeToken<List<ResponseData>>() {

        }
        val jsonData = "[{\"_id\":\"5c5bb5ce54a9c166bf1c5b82\",\"company\":\"GYNKO\",\"website\":\"www.gynko.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Veniam sit ex ex esse pariatur proident non aute sunt. Aute id eiusmod aute incididunt sint est ullamco eiusmod adipisicing aliqua est. Velit aliqua occaecat enim pariatur laboris deserunt aliqua dolore fugiat dolor consequat sit occaecat pariatur. Ullamco velit laborum cillum reprehenderit Lorem magna exercitation laboris qui et aute nulla veniam. Reprehenderit nulla culpa elit ad ex ex sunt nisi eiusmod. Non officia ea est exercitation ut Lorem aute nulla.\",\"members\":[{\"_id\":\"5c5bb5ce9ea1ae34c3d4f0c7\",\"age\":26,\"name\":{\"first\":\"Heather\",\"last\":\"Russell\"},\"email\":\"heather.russell@undefined.info\",\"phone\":\"+1 (827) 549-3643\"},{\"_id\":\"5c5bb5ce591915de62e56bb2\",\"age\":30,\"name\":{\"first\":\"Velma\",\"last\":\"Hayden\"},\"email\":\"velma.hayden@undefined.me\",\"phone\":\"+1 (989) 537-3158\"},{\"_id\":\"5c5bb5cee24406f77af3c0d1\",\"age\":30,\"name\":{\"first\":\"Solomon\",\"last\":\"Griffith\"},\"email\":\"solomon.griffith@undefined.biz\",\"phone\":\"+1 (856) 511-3589\"},{\"_id\":\"5c5bb5ceec5d62903407ab9a\",\"age\":24,\"name\":{\"first\":\"Keller\",\"last\":\"Rasmussen\"},\"email\":\"keller.rasmussen@undefined.net\",\"phone\":\"+1 (984) 488-2723\"},{\"_id\":\"5c5bb5cef7469c98a1e8f320\",\"age\":24,\"name\":{\"first\":\"Stacie\",\"last\":\"Cardenas\"},\"email\":\"stacie.cardenas@undefined.com\",\"phone\":\"+1 (848) 487-2881\"}]},{\"_id\":\"5c5bb5ce408a811f9597f614\",\"company\":\"XIXAN\",\"website\":\"www.xixan.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Mollit sit laborum cillum eu Lorem culpa cupidatat. Elit commodo mollit eiusmod ex non Lorem et laboris incididunt consectetur ea. Minim ad minim nulla consequat sunt laborum adipisicing non non culpa est excepteur. Tempor fugiat eu nulla anim do pariatur cillum esse aliquip officia. Veniam esse excepteur mollit aute. In nostrud ipsum laborum occaecat deserunt eu fugiat exercitation cillum occaecat veniam sint consequat.\",\"members\":[{\"_id\":\"5c5bb5ce9e701c476f62c7eb\",\"age\":32,\"name\":{\"first\":\"Ford\",\"last\":\"Conway\"},\"email\":\"ford.conway@undefined.tv\",\"phone\":\"+1 (937) 547-2408\"},{\"_id\":\"5c5bb5ce90f77d39025f74fa\",\"age\":38,\"name\":{\"first\":\"Burch\",\"last\":\"Dominguez\"},\"email\":\"burch.dominguez@undefined.name\",\"phone\":\"+1 (912) 539-3018\"},{\"_id\":\"5c5bb5ce57467cad3e354db1\",\"age\":26,\"name\":{\"first\":\"Earnestine\",\"last\":\"Valentine\"},\"email\":\"earnestine.valentine@undefined.us\",\"phone\":\"+1 (857) 507-3586\"},{\"_id\":\"5c5bb5ce524734923dcfc01a\",\"age\":20,\"name\":{\"first\":\"Jefferson\",\"last\":\"Jacobson\"},\"email\":\"jefferson.jacobson@undefined.io\",\"phone\":\"+1 (856) 594-3461\"},{\"_id\":\"5c5bb5cefe4c9c5fac096df2\",\"age\":32,\"name\":{\"first\":\"Bender\",\"last\":\"Guy\"},\"email\":\"bender.guy@undefined.org\",\"phone\":\"+1 (848) 492-2761\"}]},{\"_id\":\"5c5bb5ce438b8982cc91aa8d\",\"company\":\"EXERTA\",\"website\":\"www.exerta.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Irure nulla eiusmod tempor dolore amet. Deserunt ullamco ullamco duis anim nostrud laborum laborum. Mollit exercitation non reprehenderit occaecat mollit duis non aliquip.\",\"members\":[{\"_id\":\"5c5bb5ce2b7e1e6507514880\",\"age\":22,\"name\":{\"first\":\"Brooks\",\"last\":\"Andrews\"},\"email\":\"brooks.andrews@undefined.info\",\"phone\":\"+1 (955) 492-3282\"},{\"_id\":\"5c5bb5ce0234a99dd1fb669d\",\"age\":38,\"name\":{\"first\":\"Zimmerman\",\"last\":\"Riddle\"},\"email\":\"zimmerman.riddle@undefined.me\",\"phone\":\"+1 (922) 439-3956\"},{\"_id\":\"5c5bb5ce098e281001bf7586\",\"age\":36,\"name\":{\"first\":\"Guadalupe\",\"last\":\"Rose\"},\"email\":\"guadalupe.rose@undefined.biz\",\"phone\":\"+1 (868) 444-2411\"},{\"_id\":\"5c5bb5ce0d8f9a52d027bc8e\",\"age\":36,\"name\":{\"first\":\"Ilene\",\"last\":\"Olsen\"},\"email\":\"ilene.olsen@undefined.net\",\"phone\":\"+1 (842) 483-3274\"},{\"_id\":\"5c5bb5ce250afffc7f905652\",\"age\":35,\"name\":{\"first\":\"Goldie\",\"last\":\"Compton\"},\"email\":\"goldie.compton@undefined.com\",\"phone\":\"+1 (853) 553-3818\"}]},{\"_id\":\"5c5bb5ce15fd6b80ed084842\",\"company\":\"TURNLING\",\"website\":\"www.turnling.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Dolore sint id veniam cupidatat cupidatat ullamco excepteur velit. Enim in aliqua qui enim cillum. Aute nisi commodo duis voluptate consectetur. Velit labore velit irure magna laborum fugiat nulla velit qui amet magna veniam. Culpa eiusmod in tempor eu est velit Lorem veniam esse ut adipisicing. Voluptate reprehenderit incididunt magna aliquip sunt aliqua nulla cillum officia commodo labore exercitation adipisicing quis. Ipsum consequat labore fugiat fugiat eiusmod ipsum minim cillum ipsum enim sit esse mollit aliquip.\",\"members\":[{\"_id\":\"5c5bb5ce2d5287241f91f5fb\",\"age\":23,\"name\":{\"first\":\"Gale\",\"last\":\"Koch\"},\"email\":\"gale.koch@undefined.tv\",\"phone\":\"+1 (967) 459-3487\"},{\"_id\":\"5c5bb5cea8e79c0fa1224e19\",\"age\":25,\"name\":{\"first\":\"Stefanie\",\"last\":\"Hendricks\"},\"email\":\"stefanie.hendricks@undefined.name\",\"phone\":\"+1 (935) 580-3573\"},{\"_id\":\"5c5bb5ceaf489a1f5baa12dc\",\"age\":34,\"name\":{\"first\":\"Alisha\",\"last\":\"Tillman\"},\"email\":\"alisha.tillman@undefined.us\",\"phone\":\"+1 (878) 493-3488\"},{\"_id\":\"5c5bb5cee93e84714ce3dd5d\",\"age\":36,\"name\":{\"first\":\"Levy\",\"last\":\"Horn\"},\"email\":\"levy.horn@undefined.io\",\"phone\":\"+1 (817) 595-2784\"},{\"_id\":\"5c5bb5ce269ab7b5f27523cc\",\"age\":36,\"name\":{\"first\":\"Cohen\",\"last\":\"Calhoun\"},\"email\":\"cohen.calhoun@undefined.org\",\"phone\":\"+1 (817) 530-3105\"}]},{\"_id\":\"5c5bb5ce3636b60cd5a53fda\",\"company\":\"VALPREAL\",\"website\":\"www.valpreal.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Incididunt nulla deserunt commodo ullamco laborum voluptate tempor. Officia minim aliqua ut fugiat veniam mollit esse nulla officia dolore id laboris eiusmod id. In esse laboris enim enim commodo officia nisi.\",\"members\":[{\"_id\":\"5c5bb5cec7fed629060a25ba\",\"age\":29,\"name\":{\"first\":\"Noelle\",\"last\":\"Ramos\"},\"email\":\"noelle.ramos@undefined.info\",\"phone\":\"+1 (937) 446-2060\"},{\"_id\":\"5c5bb5ced6f8aebbb4289194\",\"age\":21,\"name\":{\"first\":\"Karen\",\"last\":\"Guerra\"},\"email\":\"karen.guerra@undefined.me\",\"phone\":\"+1 (965) 581-3975\"},{\"_id\":\"5c5bb5ce6f8403ab6d7fb4a2\",\"age\":39,\"name\":{\"first\":\"Gardner\",\"last\":\"Ball\"},\"email\":\"gardner.ball@undefined.biz\",\"phone\":\"+1 (843) 497-3862\"},{\"_id\":\"5c5bb5ce633d1afb560aba8f\",\"age\":21,\"name\":{\"first\":\"Taylor\",\"last\":\"Jefferson\"},\"email\":\"taylor.jefferson@undefined.net\",\"phone\":\"+1 (990) 412-3663\"},{\"_id\":\"5c5bb5cea88263340b9f6f1a\",\"age\":35,\"name\":{\"first\":\"Therese\",\"last\":\"Glass\"},\"email\":\"therese.glass@undefined.com\",\"phone\":\"+1 (901) 566-3112\"}]},{\"_id\":\"5c5bb5ce20582b1297a5e6ad\",\"company\":\"ZIDANT\",\"website\":\"www.zidant.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Incididunt amet duis consectetur veniam est reprehenderit aute nisi quis dolor ex. Non esse aute id enim. Est do ullamco commodo occaecat nulla. Voluptate ipsum voluptate sint do consequat velit est ex amet ex anim quis amet cupidatat. Elit proident occaecat nisi reprehenderit occaecat. Labore anim do do laboris. Deserunt laborum eu commodo magna nisi.\",\"members\":[{\"_id\":\"5c5bb5ce837e1475730306da\",\"age\":23,\"name\":{\"first\":\"Mathews\",\"last\":\"Navarro\"},\"email\":\"mathews.navarro@undefined.tv\",\"phone\":\"+1 (864) 499-2273\"},{\"_id\":\"5c5bb5cef1ff505998389180\",\"age\":32,\"name\":{\"first\":\"Kelly\",\"last\":\"Padilla\"},\"email\":\"kelly.padilla@undefined.name\",\"phone\":\"+1 (880) 452-2427\"},{\"_id\":\"5c5bb5ce6afc1735f55105fa\",\"age\":23,\"name\":{\"first\":\"Millicent\",\"last\":\"Shaffer\"},\"email\":\"millicent.shaffer@undefined.us\",\"phone\":\"+1 (982) 517-3222\"},{\"_id\":\"5c5bb5ceed820af7461f1435\",\"age\":20,\"name\":{\"first\":\"Howe\",\"last\":\"Newman\"},\"email\":\"howe.newman@undefined.io\",\"phone\":\"+1 (893) 453-3620\"},{\"_id\":\"5c5bb5ce937a3af222cc926f\",\"age\":28,\"name\":{\"first\":\"Daniels\",\"last\":\"Preston\"},\"email\":\"daniels.preston@undefined.org\",\"phone\":\"+1 (974) 575-3235\"}]},{\"_id\":\"5c5bb5cea3110f2013d72016\",\"company\":\"ZUVY\",\"website\":\"www.zuvy.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Eu cillum sint do adipisicing in irure labore ad qui mollit velit. Nulla elit mollit ea incididunt minim in fugiat reprehenderit ex cillum sint nostrud. Ullamco proident non qui anim. Ullamco do dolore sit do. Enim incididunt commodo velit dolor eu ex in qui qui amet enim eiusmod magna. Deserunt pariatur sint commodo nulla irure do laboris fugiat officia deserunt do nisi non.\",\"members\":[{\"_id\":\"5c5bb5ce7a94cdd74d64103e\",\"age\":29,\"name\":{\"first\":\"Fuller\",\"last\":\"Perry\"},\"email\":\"fuller.perry@undefined.info\",\"phone\":\"+1 (906) 503-3762\"},{\"_id\":\"5c5bb5ce7ea73315fcfc48b1\",\"age\":38,\"name\":{\"first\":\"Gould\",\"last\":\"Murray\"},\"email\":\"gould.murray@undefined.me\",\"phone\":\"+1 (952) 440-2182\"},{\"_id\":\"5c5bb5ceb5f5f81a7720222c\",\"age\":37,\"name\":{\"first\":\"Wilkinson\",\"last\":\"Walters\"},\"email\":\"wilkinson.walters@undefined.biz\",\"phone\":\"+1 (881) 438-2138\"},{\"_id\":\"5c5bb5ceb0b55d607d03f6e7\",\"age\":20,\"name\":{\"first\":\"Alta\",\"last\":\"Gillespie\"},\"email\":\"alta.gillespie@undefined.net\",\"phone\":\"+1 (825) 418-2299\"},{\"_id\":\"5c5bb5ce4238fdf625069ae0\",\"age\":25,\"name\":{\"first\":\"Janelle\",\"last\":\"Anderson\"},\"email\":\"janelle.anderson@undefined.com\",\"phone\":\"+1 (982) 557-3380\"}]},{\"_id\":\"5c5bb5ce3cb49704fed9125d\",\"company\":\"ZILLACOM\",\"website\":\"www.zillacom.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Nulla tempor id culpa esse aliquip ea enim id officia Lorem qui cupidatat. Cillum consequat excepteur dolore et adipisicing. Cupidatat ex ullamco aliqua ad reprehenderit cillum Lorem cupidatat veniam elit eu duis nostrud est. Occaecat fugiat in dolor et esse exercitation do minim sunt voluptate Lorem nulla deserunt.\",\"members\":[{\"_id\":\"5c5bb5ce9578eff7748d7a9a\",\"age\":31,\"name\":{\"first\":\"Emma\",\"last\":\"Atkins\"},\"email\":\"emma.atkins@undefined.tv\",\"phone\":\"+1 (916) 589-2403\"},{\"_id\":\"5c5bb5ce6104423bc7bf1ec4\",\"age\":33,\"name\":{\"first\":\"Mona\",\"last\":\"Hatfield\"},\"email\":\"mona.hatfield@undefined.name\",\"phone\":\"+1 (995) 513-2062\"},{\"_id\":\"5c5bb5cece9f21f07142c04b\",\"age\":35,\"name\":{\"first\":\"Nadia\",\"last\":\"Grimes\"},\"email\":\"nadia.grimes@undefined.us\",\"phone\":\"+1 (871) 437-2222\"},{\"_id\":\"5c5bb5ce5543f54fbf53dff6\",\"age\":37,\"name\":{\"first\":\"Sheppard\",\"last\":\"Hurley\"},\"email\":\"sheppard.hurley@undefined.io\",\"phone\":\"+1 (949) 593-2563\"},{\"_id\":\"5c5bb5ce8415cda567c9e28c\",\"age\":24,\"name\":{\"first\":\"Elba\",\"last\":\"Blackwell\"},\"email\":\"elba.blackwell@undefined.org\",\"phone\":\"+1 (882) 405-3731\"}]},{\"_id\":\"5c5bb5ce500be5efd5a7a525\",\"company\":\"KAGE\",\"website\":\"www.kage.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Enim commodo exercitation Lorem quis officia nulla sit anim anim. Est consectetur qui sunt incididunt voluptate deserunt. Anim nisi culpa adipisicing esse nostrud officia laborum duis irure ex non amet consequat sunt.\",\"members\":[{\"_id\":\"5c5bb5ce8856add6468d3422\",\"age\":22,\"name\":{\"first\":\"Dixon\",\"last\":\"Mills\"},\"email\":\"dixon.mills@undefined.info\",\"phone\":\"+1 (837) 461-2996\"},{\"_id\":\"5c5bb5ce3138ae7636ce24d6\",\"age\":38,\"name\":{\"first\":\"Jeanette\",\"last\":\"Travis\"},\"email\":\"jeanette.travis@undefined.me\",\"phone\":\"+1 (855) 561-3112\"},{\"_id\":\"5c5bb5ce009ab8048e630026\",\"age\":38,\"name\":{\"first\":\"Genevieve\",\"last\":\"Dawson\"},\"email\":\"genevieve.dawson@undefined.biz\",\"phone\":\"+1 (934) 451-3624\"},{\"_id\":\"5c5bb5ce7d7f5de9dcf6198e\",\"age\":37,\"name\":{\"first\":\"Anita\",\"last\":\"Macdonald\"},\"email\":\"anita.macdonald@undefined.net\",\"phone\":\"+1 (900) 465-3791\"},{\"_id\":\"5c5bb5ce54b102ccde93e955\",\"age\":23,\"name\":{\"first\":\"Shelton\",\"last\":\"Hardin\"},\"email\":\"shelton.hardin@undefined.com\",\"phone\":\"+1 (860) 507-3936\"}]},{\"_id\":\"5c5bb5ce324acd7daf9122c7\",\"company\":\"CANDECOR\",\"website\":\"www.candecor.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Laborum eu Lorem anim nulla. Ea voluptate laborum minim cupidatat sint tempor in in et commodo duis. Sint ipsum ea laborum non est tempor deserunt tempor consectetur sunt sunt.\",\"members\":[{\"_id\":\"5c5bb5ce6d79ea3b8f482fb1\",\"age\":32,\"name\":{\"first\":\"Eunice\",\"last\":\"Strickland\"},\"email\":\"eunice.strickland@undefined.tv\",\"phone\":\"+1 (991) 479-2389\"},{\"_id\":\"5c5bb5ce1c614d6aab86d113\",\"age\":30,\"name\":{\"first\":\"Joan\",\"last\":\"Franklin\"},\"email\":\"joan.franklin@undefined.name\",\"phone\":\"+1 (976) 546-2168\"},{\"_id\":\"5c5bb5ce2a81679fc748f22b\",\"age\":35,\"name\":{\"first\":\"Brewer\",\"last\":\"Morgan\"},\"email\":\"brewer.morgan@undefined.us\",\"phone\":\"+1 (851) 490-3235\"},{\"_id\":\"5c5bb5ceca03bb0f8b912baf\",\"age\":25,\"name\":{\"first\":\"Carolyn\",\"last\":\"Greer\"},\"email\":\"carolyn.greer@undefined.io\",\"phone\":\"+1 (800) 555-2948\"},{\"_id\":\"5c5bb5ce66ec2558f13042f0\",\"age\":37,\"name\":{\"first\":\"Moss\",\"last\":\"Mccarthy\"},\"email\":\"moss.mccarthy@undefined.org\",\"phone\":\"+1 (972) 408-2961\"}]},{\"_id\":\"5c5bb5ce126138c62bca736e\",\"company\":\"CENTREGY\",\"website\":\"www.centregy.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Nulla sit duis elit commodo consequat officia. Aliqua nisi cillum commodo do duis enim nostrud occaecat. Esse qui fugiat sit qui. Aute consectetur eu cillum qui exercitation nulla dolore amet officia occaecat. Duis dolore pariatur aliqua deserunt.\",\"members\":[{\"_id\":\"5c5bb5ce337cd3ded5e48a27\",\"age\":35,\"name\":{\"first\":\"Winifred\",\"last\":\"Scott\"},\"email\":\"winifred.scott@undefined.info\",\"phone\":\"+1 (918) 515-2413\"},{\"_id\":\"5c5bb5ce2c87dc71d7b01d93\",\"age\":21,\"name\":{\"first\":\"Tara\",\"last\":\"Bridges\"},\"email\":\"tara.bridges@undefined.me\",\"phone\":\"+1 (921) 598-3393\"},{\"_id\":\"5c5bb5cef6750711362d0eee\",\"age\":40,\"name\":{\"first\":\"Bird\",\"last\":\"Owen\"},\"email\":\"bird.owen@undefined.biz\",\"phone\":\"+1 (829) 403-3784\"},{\"_id\":\"5c5bb5cec8100a2efa9653b5\",\"age\":30,\"name\":{\"first\":\"Bruce\",\"last\":\"Skinner\"},\"email\":\"bruce.skinner@undefined.net\",\"phone\":\"+1 (899) 580-2616\"},{\"_id\":\"5c5bb5ce67b1e6969d7674b1\",\"age\":20,\"name\":{\"first\":\"Delaney\",\"last\":\"Stafford\"},\"email\":\"delaney.stafford@undefined.com\",\"phone\":\"+1 (951) 430-3753\"}]},{\"_id\":\"5c5bb5ce0f45a2163d5ba8c6\",\"company\":\"SOPRANO\",\"website\":\"www.soprano.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Cupidatat culpa deserunt id fugiat fugiat eiusmod et. Quis non veniam mollit eiusmod incididunt enim commodo ipsum excepteur laborum. Dolore occaecat Lorem occaecat laboris adipisicing ea nostrud aliqua.\",\"members\":[{\"_id\":\"5c5bb5ce84d9bfaddf12d271\",\"age\":27,\"name\":{\"first\":\"Conrad\",\"last\":\"Hendrix\"},\"email\":\"conrad.hendrix@undefined.tv\",\"phone\":\"+1 (893) 572-3825\"},{\"_id\":\"5c5bb5ceec2a78e610fad1e8\",\"age\":20,\"name\":{\"first\":\"Patti\",\"last\":\"Hudson\"},\"email\":\"patti.hudson@undefined.name\",\"phone\":\"+1 (866) 591-3616\"},{\"_id\":\"5c5bb5ceb767fc694192ee82\",\"age\":36,\"name\":{\"first\":\"Felicia\",\"last\":\"Oliver\"},\"email\":\"felicia.oliver@undefined.us\",\"phone\":\"+1 (878) 536-2436\"},{\"_id\":\"5c5bb5ce01f841cf3e49aa13\",\"age\":39,\"name\":{\"first\":\"Shelley\",\"last\":\"Joyner\"},\"email\":\"shelley.joyner@undefined.io\",\"phone\":\"+1 (960) 562-3776\"},{\"_id\":\"5c5bb5ce2fc11b3de4a0f0e7\",\"age\":38,\"name\":{\"first\":\"Bonnie\",\"last\":\"Briggs\"},\"email\":\"bonnie.briggs@undefined.org\",\"phone\":\"+1 (948) 450-2909\"}]},{\"_id\":\"5c5bb5ce650e806d290b0675\",\"company\":\"ECRATER\",\"website\":\"www.ecrater.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Ut nostrud deserunt culpa exercitation duis eiusmod veniam sunt irure exercitation. Nostrud tempor sunt ea consectetur adipisicing quis occaecat ad. Laborum id pariatur labore Lorem esse exercitation fugiat cupidatat cupidatat reprehenderit exercitation do. Consectetur ullamco mollit elit deserunt excepteur elit incididunt fugiat reprehenderit nulla deserunt ex sit ullamco. Duis consequat deserunt quis velit commodo eiusmod amet. Excepteur duis occaecat elit non laborum cupidatat ullamco id ipsum sint enim velit.\",\"members\":[{\"_id\":\"5c5bb5ce16bce47a46325a40\",\"age\":26,\"name\":{\"first\":\"Rodriquez\",\"last\":\"Riggs\"},\"email\":\"rodriquez.riggs@undefined.info\",\"phone\":\"+1 (999) 588-3076\"},{\"_id\":\"5c5bb5ce484cec59af913ca8\",\"age\":26,\"name\":{\"first\":\"Wall\",\"last\":\"Yates\"},\"email\":\"wall.yates@undefined.me\",\"phone\":\"+1 (836) 430-2285\"},{\"_id\":\"5c5bb5ce96ee028b8ec0c035\",\"age\":35,\"name\":{\"first\":\"Concetta\",\"last\":\"Brooks\"},\"email\":\"concetta.brooks@undefined.biz\",\"phone\":\"+1 (946) 506-3751\"},{\"_id\":\"5c5bb5ceb664a616e67f454e\",\"age\":38,\"name\":{\"first\":\"Pearl\",\"last\":\"Gallegos\"},\"email\":\"pearl.gallegos@undefined.net\",\"phone\":\"+1 (895) 501-2872\"},{\"_id\":\"5c5bb5ceea0e9ea4f3f9dfc5\",\"age\":24,\"name\":{\"first\":\"Winters\",\"last\":\"Delgado\"},\"email\":\"winters.delgado@undefined.com\",\"phone\":\"+1 (978) 469-3354\"}]},{\"_id\":\"5c5bb5ceeb0960af37cd4a5b\",\"company\":\"ARTIQ\",\"website\":\"www.artiq.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Quis cupidatat qui Lorem in enim exercitation adipisicing aliqua ad culpa ea laboris minim nisi. Veniam mollit voluptate aliqua culpa sint dolor ut ipsum incididunt labore nulla deserunt ipsum nulla. Consequat mollit irure sit id mollit sint reprehenderit irure ex reprehenderit consectetur cillum.\",\"members\":[{\"_id\":\"5c5bb5ce991a25932057f3ca\",\"age\":20,\"name\":{\"first\":\"Maria\",\"last\":\"Charles\"},\"email\":\"maria.charles@undefined.tv\",\"phone\":\"+1 (922) 597-2018\"},{\"_id\":\"5c5bb5cefb99494b6544bb15\",\"age\":35,\"name\":{\"first\":\"Bennett\",\"last\":\"Bennett\"},\"email\":\"bennett.bennett@undefined.name\",\"phone\":\"+1 (860) 502-3317\"},{\"_id\":\"5c5bb5ce435018c742e8a98e\",\"age\":23,\"name\":{\"first\":\"Judith\",\"last\":\"Freeman\"},\"email\":\"judith.freeman@undefined.us\",\"phone\":\"+1 (970) 442-3093\"},{\"_id\":\"5c5bb5cee912edc1a27525b3\",\"age\":39,\"name\":{\"first\":\"Ester\",\"last\":\"Cook\"},\"email\":\"ester.cook@undefined.io\",\"phone\":\"+1 (870) 571-3025\"},{\"_id\":\"5c5bb5ceb63e41ac1a993783\",\"age\":20,\"name\":{\"first\":\"Samantha\",\"last\":\"Collins\"},\"email\":\"samantha.collins@undefined.org\",\"phone\":\"+1 (890) 496-3358\"}]},{\"_id\":\"5c5bb5ce24c2ce6a0e19267f\",\"company\":\"PRIMORDIA\",\"website\":\"www.primordia.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Dolore ut pariatur culpa aliquip mollit nostrud amet. Ipsum aliqua ea in anim aute aliqua sit nisi eiusmod laboris velit. Tempor velit fugiat sit velit. Sint id et ad est fugiat incididunt ea voluptate ea ex non ex voluptate. Ea ullamco ex exercitation commodo sit commodo est. Laboris cupidatat officia ex adipisicing enim consectetur irure ex deserunt cupidatat et nostrud aliquip. Amet elit sint aliquip eiusmod aliquip pariatur ea officia adipisicing eu culpa.\",\"members\":[{\"_id\":\"5c5bb5cee22a771d1ab784ff\",\"age\":36,\"name\":{\"first\":\"Jensen\",\"last\":\"Christian\"},\"email\":\"jensen.christian@undefined.info\",\"phone\":\"+1 (844) 600-3513\"},{\"_id\":\"5c5bb5ce8712320aa5c12b3b\",\"age\":25,\"name\":{\"first\":\"Orr\",\"last\":\"Clayton\"},\"email\":\"orr.clayton@undefined.me\",\"phone\":\"+1 (829) 482-2828\"},{\"_id\":\"5c5bb5ce62d1e478e79d1222\",\"age\":31,\"name\":{\"first\":\"Theresa\",\"last\":\"Davenport\"},\"email\":\"theresa.davenport@undefined.biz\",\"phone\":\"+1 (954) 439-2448\"},{\"_id\":\"5c5bb5ceadac8e94aa0cefaa\",\"age\":31,\"name\":{\"first\":\"Henry\",\"last\":\"Peters\"},\"email\":\"henry.peters@undefined.net\",\"phone\":\"+1 (842) 581-2354\"},{\"_id\":\"5c5bb5cee75585e5ff91ce43\",\"age\":39,\"name\":{\"first\":\"Ruiz\",\"last\":\"Thomas\"},\"email\":\"ruiz.thomas@undefined.com\",\"phone\":\"+1 (952) 524-2938\"}]},{\"_id\":\"5c5bb5cefe3cc4c105661396\",\"company\":\"SPLINX\",\"website\":\"www.splinx.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Ad aliqua quis ipsum mollit ea aliquip. Ea est incididunt sint cupidatat anim fugiat est aute magna cillum Lorem velit consectetur. Reprehenderit ex amet ipsum elit velit tempor amet. Sit qui dolor culpa tempor ea. Labore nisi elit commodo sunt. Fugiat enim aliquip exercitation ut pariatur irure amet aliqua. Id aliqua eu cupidatat anim enim labore quis culpa eiusmod ipsum et.\",\"members\":[{\"_id\":\"5c5bb5ce8217ec55781ce522\",\"age\":21,\"name\":{\"first\":\"Evans\",\"last\":\"Barnett\"},\"email\":\"evans.barnett@undefined.tv\",\"phone\":\"+1 (866) 484-2040\"},{\"_id\":\"5c5bb5ce2248e84f621a185b\",\"age\":38,\"name\":{\"first\":\"Isabelle\",\"last\":\"Cummings\"},\"email\":\"isabelle.cummings@undefined.name\",\"phone\":\"+1 (821) 503-3895\"},{\"_id\":\"5c5bb5ce5384e166a355bd2b\",\"age\":26,\"name\":{\"first\":\"Valeria\",\"last\":\"Sheppard\"},\"email\":\"valeria.sheppard@undefined.us\",\"phone\":\"+1 (949) 407-3205\"},{\"_id\":\"5c5bb5cea95ff87fab899656\",\"age\":31,\"name\":{\"first\":\"Valencia\",\"last\":\"Morin\"},\"email\":\"valencia.morin@undefined.io\",\"phone\":\"+1 (878) 546-3921\"},{\"_id\":\"5c5bb5ced431869ddd7f6fd6\",\"age\":35,\"name\":{\"first\":\"Erickson\",\"last\":\"Buckley\"},\"email\":\"erickson.buckley@undefined.org\",\"phone\":\"+1 (906) 591-3818\"}]},{\"_id\":\"5c5bb5ce147ebcff3feff7b7\",\"company\":\"PEARLESEX\",\"website\":\"www.pearlesex.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Eu aliquip sit elit ad adipisicing amet. Eiusmod qui laborum occaecat excepteur ea quis nulla. Fugiat exercitation culpa eiusmod sit et non sunt est est sunt cillum adipisicing.\",\"members\":[{\"_id\":\"5c5bb5ce6f9b070a3eb00435\",\"age\":32,\"name\":{\"first\":\"Loretta\",\"last\":\"Whitney\"},\"email\":\"loretta.whitney@undefined.info\",\"phone\":\"+1 (928) 450-2211\"},{\"_id\":\"5c5bb5cefd277262a5c97ee8\",\"age\":31,\"name\":{\"first\":\"Angelita\",\"last\":\"Cantrell\"},\"email\":\"angelita.cantrell@undefined.me\",\"phone\":\"+1 (821) 558-2958\"},{\"_id\":\"5c5bb5ce46180ab8f667a691\",\"age\":36,\"name\":{\"first\":\"Lori\",\"last\":\"Mclean\"},\"email\":\"lori.mclean@undefined.biz\",\"phone\":\"+1 (976) 527-3538\"},{\"_id\":\"5c5bb5ce5153afb0e5953c05\",\"age\":28,\"name\":{\"first\":\"Miller\",\"last\":\"Ward\"},\"email\":\"miller.ward@undefined.net\",\"phone\":\"+1 (943) 465-2556\"},{\"_id\":\"5c5bb5ce96e8dddb3818aa31\",\"age\":37,\"name\":{\"first\":\"Tyson\",\"last\":\"Knox\"},\"email\":\"tyson.knox@undefined.com\",\"phone\":\"+1 (998) 420-3031\"}]},{\"_id\":\"5c5bb5ce8d2e883dec836eca\",\"company\":\"ISOLOGIA\",\"website\":\"www.isologia.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Qui non duis ex commodo duis sint consectetur. Dolore labore aliqua consequat sit cillum culpa anim laboris officia nostrud. Qui eiusmod non pariatur cupidatat magna culpa amet commodo tempor exercitation exercitation qui.\",\"members\":[{\"_id\":\"5c5bb5ce95e358dbfe600ae1\",\"age\":21,\"name\":{\"first\":\"Petersen\",\"last\":\"Goodman\"},\"email\":\"petersen.goodman@undefined.tv\",\"phone\":\"+1 (989) 402-3325\"},{\"_id\":\"5c5bb5ce875207f5e0c18ca3\",\"age\":26,\"name\":{\"first\":\"Eileen\",\"last\":\"Smith\"},\"email\":\"eileen.smith@undefined.name\",\"phone\":\"+1 (942) 496-3945\"},{\"_id\":\"5c5bb5ce7dc4fd6e1e6eb184\",\"age\":40,\"name\":{\"first\":\"Celeste\",\"last\":\"Holt\"},\"email\":\"celeste.holt@undefined.us\",\"phone\":\"+1 (858) 583-3450\"},{\"_id\":\"5c5bb5cedf4a7785efcbdce8\",\"age\":20,\"name\":{\"first\":\"Allison\",\"last\":\"Gibbs\"},\"email\":\"allison.gibbs@undefined.io\",\"phone\":\"+1 (938) 556-2487\"},{\"_id\":\"5c5bb5ce3be2048e4b53f753\",\"age\":24,\"name\":{\"first\":\"Rosa\",\"last\":\"Simmons\"},\"email\":\"rosa.simmons@undefined.org\",\"phone\":\"+1 (986) 464-3338\"}]},{\"_id\":\"5c5bb5ce28a6a5c43f5ec367\",\"company\":\"DEMINIMUM\",\"website\":\"www.deminimum.co.uk\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Occaecat ex occaecat voluptate cupidatat sint ut consectetur labore cupidatat. Consequat reprehenderit dolor quis ea excepteur cillum anim exercitation magna veniam magna magna. Ad nostrud adipisicing eu dolore tempor proident sint nisi irure eu. Magna magna veniam esse mollit quis irure Lorem aliqua sunt eu veniam fugiat duis. Incididunt consequat ut sunt enim ex. Exercitation adipisicing cillum mollit tempor.\",\"members\":[{\"_id\":\"5c5bb5ce20b5a4ecf8b1ff34\",\"age\":38,\"name\":{\"first\":\"Katelyn\",\"last\":\"Davis\"},\"email\":\"katelyn.davis@undefined.info\",\"phone\":\"+1 (903) 499-2265\"},{\"_id\":\"5c5bb5ce50f0f293bfbbfed2\",\"age\":21,\"name\":{\"first\":\"Chen\",\"last\":\"Woodward\"},\"email\":\"chen.woodward@undefined.me\",\"phone\":\"+1 (933) 459-3879\"},{\"_id\":\"5c5bb5ced11227355bd76d4f\",\"age\":29,\"name\":{\"first\":\"Mann\",\"last\":\"Powers\"},\"email\":\"mann.powers@undefined.biz\",\"phone\":\"+1 (830) 584-2610\"},{\"_id\":\"5c5bb5ce8bc4334ced9d3381\",\"age\":31,\"name\":{\"first\":\"Rosie\",\"last\":\"Becker\"},\"email\":\"rosie.becker@undefined.net\",\"phone\":\"+1 (823) 437-3960\"},{\"_id\":\"5c5bb5ce70acca80f4073759\",\"age\":36,\"name\":{\"first\":\"Strickland\",\"last\":\"Conner\"},\"email\":\"strickland.conner@undefined.com\",\"phone\":\"+1 (998) 418-3845\"}]},{\"_id\":\"5c5bb5cec49bf7e8b3a6ea6a\",\"company\":\"SYNTAC\",\"website\":\"www.syntac.ca\",\"logo\":\"http://placehold.it/32x32\",\"about\":\"Labore magna incididunt deserunt do enim officia sunt ut voluptate ad Lorem quis dolor. Consequat consectetur mollit reprehenderit eu nostrud Lorem cupidatat ad sit. Magna occaecat ex incididunt nostrud tempor sit anim. Nulla veniam laborum id non cillum officia ex fugiat proident ex. Consectetur id irure ullamco eiusmod Lorem qui nostrud.\",\"members\":[{\"_id\":\"5c5bb5ce0df7b90d647064dc\",\"age\":37,\"name\":{\"first\":\"Wendy\",\"last\":\"Lloyd\"},\"email\":\"wendy.lloyd@undefined.tv\",\"phone\":\"+1 (985) 531-3134\"},{\"_id\":\"5c5bb5ceaa8042c4a01b40a2\",\"age\":32,\"name\":{\"first\":\"Ewing\",\"last\":\"Hood\"},\"email\":\"ewing.hood@undefined.name\",\"phone\":\"+1 (864) 421-3683\"},{\"_id\":\"5c5bb5cea8ade39029726005\",\"age\":26,\"name\":{\"first\":\"Pratt\",\"last\":\"Vazquez\"},\"email\":\"pratt.vazquez@undefined.us\",\"phone\":\"+1 (800) 566-3534\"},{\"_id\":\"5c5bb5ce582bc074e03fcd84\",\"age\":21,\"name\":{\"first\":\"Sheryl\",\"last\":\"Rosa\"},\"email\":\"sheryl.rosa@undefined.io\",\"phone\":\"+1 (866) 413-3916\"},{\"_id\":\"5c5bb5ce863dd7ebe1f03a5a\",\"age\":35,\"name\":{\"first\":\"Mack\",\"last\":\"Robinson\"},\"email\":\"mack.robinson@undefined.org\",\"phone\":\"+1 (986) 501-2226\"}]}]"
        val gson = Gson()
        return gson.fromJson(jsonData,token.type)

    }



    @Test
    fun getDataFromRemote() {

        Mockito.`when`(repository.getDataFromApi()).thenReturn(Single.just(getObject()))

        val testObserver = TestObserver<List<ResponseData>>()

        viewModel.generateApiCall()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { responseData -> responseData.size == 20 }
    }

    @Test
    fun sortCompanyDataTest(){
        val responseData = getObject()
        val companyDetails : MutableList<CompanyDetails> = ArrayList()
        responseData.forEach {
            companyDetails.add(CompanyDetails(it._id, it.logo,it.company))
        }
        viewModel.setCompanyDetails(companyDetails)


        viewModel.sortCompanyData()

        assert(viewModel.getCompanyDetails()[0].name.startsWith("A"))
    }


    @Test
    fun filterCompanyDetailsTest(){
        val responseData = getObject()
        val companyDetails : MutableList<CompanyDetails> = ArrayList()
        responseData.forEach {
            companyDetails.add(CompanyDetails(it._id, it.logo,it.company))
        }
        viewModel.setCompanyDetails(companyDetails)

        assert(viewModel.filterCompany("X")[0].name.startsWith("X"))


    }

    @Test
    fun findCompanyDetailsTest(){
        val responseData = getObject()
        val companyDetails : MutableList<CompanyDetails> = ArrayList()
        responseData.forEach {
            companyDetails.add(CompanyDetails(it._id, it.logo,it.company))
        }
        viewModel.setCompanyDetails(companyDetails)

        assert(viewModel.findCSingleCompanyData("5c5bb5ce54a9c166bf1c5b82")!!.company == "GYNKO")
    }



}