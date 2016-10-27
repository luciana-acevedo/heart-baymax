package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.helpers.IntentFactory;
import com.luacevedo.heartbaymax.model.patient.Patient;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;

import java.util.ArrayList;
import java.util.List;

public class PatientPageFragment extends BaseFragment implements View.OnClickListener {

    private Patient patient;
    private LinearLayout addHeartSituationLayout;
    private LinearLayout continueDiagnosisLayout;
    private LinearLayout patientContentLayout;
    private TextView patientName;

    public static PatientPageFragment newInstance(Patient patient) {
        PatientPageFragment fragment = new PatientPageFragment();
        fragment.setPatient(patient);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page, container, false);
        setupViews(view);
        addPatientAttributes();
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_content);
        patientName = (TextView) view.findViewById(R.id.patient_page_name);
        patientName.setText(patient.getName());
        addHeartSituationLayout = (LinearLayout) view.findViewById(R.id.add_heart_situation_layout);
        addHeartSituationLayout.setOnClickListener(this);
        continueDiagnosisLayout = (LinearLayout) view.findViewById(R.id.continue_diagnosis_layout);
        continueDiagnosisLayout.setOnClickListener(this);
    }

    private void addPatientAttributes() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        for (PatientAttribute attribute : patient.getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.SECONDARY_SYMPTOMS:
                    secondarySymptomsList.add(attribute);
                    break;
                default:
                    break;
            }
        }

        addValuesToLayout(essentialSymptomsList);
        addValuesToLayout(secondarySymptomsList);
    }

    private void addValuesToLayout(List<PatientAttribute> list) {
        for (PatientAttribute attribute : list) {
            PatientAttributeView viewAttribute = new PatientAttributeView(getActivity());
            viewAttribute.setData(attribute);
            patientContentLayout.addView(viewAttribute);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_heart_situation_layout) {

        } else if (v.getId() == R.id.continue_diagnosis_layout) {
            startActivity(IntentFactory.getRulesExecutionActivityIntent(patient));
        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
