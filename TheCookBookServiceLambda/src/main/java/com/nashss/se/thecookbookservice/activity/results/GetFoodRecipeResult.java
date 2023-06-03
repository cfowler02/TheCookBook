public class GetItineraryActivitiesActivity {
    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new GetItineraryActivitiesActivity object.
     *
     * @param itineraryDao ItineraryDao to access the itinerary table.
     */
    @Inject
    public GetItineraryActivitiesActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }

    /**
     * This method handles the incoming request by retrieving the itinerary from the database.
     * <p>
     * It then returns the itinerary's activity list.
     *
     * @param getItineraryActivitiesRequest request object containing the itinerary email and trioName
     * @return getPlaylistSongsResult result object containing the itinerary's list of API defined {@link ActivityModel}s
     */public GetItineraryActivitiesResult handleRequest(final GetItineraryActivitiesRequest getItineraryActivitiesRequest){

        log.info("Received GetItineraryActivitiesRequest {}", getItineraryActivitiesRequest);
        String email = getItineraryActivitiesRequest.getEmail();
        String tripName= getItineraryActivitiesRequest.getTripName();
        Itinerary itinerary = itineraryDao.getItinerary(email, tripName);
        List<Activity> activityList = itinerary.getActivities();

        List<ActivityModel> activityModels = new ArrayList<>();
        for(Activity activity: activityList) {
            ActivityModel model = new VModelConverter().toActivityModel(activity);
            activityModels.add(model);
        }
        return GetItineraryActivitiesResult.builder()
                .withActivityList(activityModels)
                .build();
    }
}